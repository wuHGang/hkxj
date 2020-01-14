package cn.hkxj.platform.service;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.dao.*;
import cn.hkxj.platform.exceptions.RoomParseException;
import cn.hkxj.platform.exceptions.UrpRequestException;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.dto.CourseTimeTableDetailDto;
import cn.hkxj.platform.pojo.vo.CourseTimeTableVo;
import cn.hkxj.platform.spider.newmodel.coursetimetable.TimeAndPlace;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTable;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import cn.hkxj.platform.utils.DateUtils;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/8/29 21:40
 */
@Slf4j
@Service
public class CourseTimeTableService {

    private static final String NO_COURSE_TEXT = "今天没有课呐，可以出去浪了~\n";

    private static ThreadFactory courseTimeTableThreadFactory = new ThreadFactoryBuilder().setNameFormat("courseTimeTable-pool").build();
    private static ExecutorService saveDbPool = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), courseTimeTableThreadFactory);

    @Resource
    private RoomService roomService;
    @Resource
    private RoomDao roomDao;
    @Resource
    private PlanDao planDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private UrpCourseService urpCourseService;
    @Resource
    private CourseTimeTableDetailDao courseTimeTableDetailDao;
    @Resource
    private CourseTimeTableBasicInfoDao courseTimeTableBasicInfoDao;
    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private ClassService classService;
    @Resource
    private ClassCourseTimetableDao classCourseTimetableDao;
    @Resource
    private CourseTimeTableDao courseTimeTableDao;
    @Resource
    private StudentCourseTimeTableDao studentCourseTimeTableDao;
    @Resource
    private UrpClassRoomDao urpClassRoomDao;
    @Resource
    private TeacherCourseTimeTableDao teacherCourseTimeTableDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private UrpClassDao urpClassDao;

    private Executor courseSpiderExecutor = new MDCThreadPool(7, 7, 0L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), r -> new Thread(r, "courseSpider"));

    /**
     * 这个方法只能将一天的数据转换成当日课表所需要的文本
     *
     * @param details 当天的课程时间表详情
     * @return 课表
     */
    public String convertToText(List<CourseTimeTableDetail> details) {
        if (CollectionUtils.isEmpty(details)) {
            return NO_COURSE_TEXT;
        }
        StringBuilder builder = new StringBuilder();
        details.sort(Comparator.comparing(CourseTimeTableDetail::getOrder));
        int count = 0;
        int length = details.size();
        for (CourseTimeTableDetail detail : details) {
            if (detail == null) {
                continue;
            }
            count++;
            builder.append(computationOfKnots(detail)).append("节").append("\n")
                    .append(urpCourseService.getCurrentTermCourse(detail.getCourseId(),
                            detail.getCourseSequenceNumber()).getName()).append(
                            "\n")
                    .append(detail.getRoomName());
            if (count != length) {
                builder.append("\n\n");
            }
        }
        return builder.toString();
    }

    public List<CourseTimeTableDetailDto> getAllCourseTimeTableDetailDtos(int account) {
        Student student = studentDao.selectStudentByAccount(account);
        List<CourseTimeTableDetail> details = getAllCourseTimeTableDetails(student);
        return assembleCourseTimeTableDto(details);
    }

    public List<CourseTimeTableDetailDto> getAppointSectionCourseTimeTableDetailDto(int account, int section) {
        Student student = studentDao.selectStudentByAccount(account);
        List<CourseTimeTableDetail> details = getDetailsForSection(student, section);
        return assembleCourseTimeTableDto(details);
    }

    /**
     * 获取当前学期所有的课程时间表
     *
     * @param student 学生实体
     * @return 课程时间表详情
     */
    public List<CourseTimeTableDetail> getAllCourseTimeTableDetails(Student student) {
        SchoolTime schoolTime = DateUtils.getCurrentSchoolTime();
        List<Integer> detailIdList = getCourseTimeTableDetailIdByAccount(student.getAccount());
        if (detailIdList.isEmpty()) {
            try {
                CompletableFuture<UrpCourseTimeTableForSpider> future =
                        CompletableFuture.supplyAsync(() -> getCourseTimeTableDetails(student), courseSpiderExecutor);
                UrpCourseTimeTableForSpider tableForSpider = future.get(1000L, TimeUnit.MILLISECONDS);
                if (!hasSchoolCourse(tableForSpider)) {
                    return getCourseTimetableByClass(student);
                }
                return getCurrentTermDataFromSpider(tableForSpider, schoolTime);
            } catch (UrpRequestException | InterruptedException | ExecutionException | TimeoutException e) {
                return getCourseTimetableByClass(student);
            }

        } else {
            return courseTimeTableDetailDao.getCourseTimeTableDetailForCurrentTerm(detailIdList, schoolTime);
        }
    }


    public List<CourseTimeTableVo> updateCourseTimeTableByStudent(int account) {
        studentCourseTimeTableDao.deleteByAccount(account);
        return getCourseTimeTableByStudent(account);
    }


    public List<CourseTimeTableVo> getCourseTimeTableByStudent(int account) {
        Student student = studentDao.selectStudentByAccount(account);
        return getCourseTimeTableByStudent(student);
    }


    public List<CourseTimeTableVo> getCourseTimeTableByStudent(Student student) {
        List<Integer> idList = getCourseTimeTableIdByAccount(student.getAccount());
        if (idList.isEmpty()) {
            return getCourseTimeTableByStudentFromSpider(student);
        } else {
            return transCourseTimeTableToVo(courseTimeTableDao.selectByIdList(idList));
        }
    }

    List<CourseTimeTableVo> getCourseTimeTableByStudentFromSpider(Student student) {
        try {
            CompletableFuture<UrpCourseTimeTableForSpider> future =
                    CompletableFuture.supplyAsync(() -> getCourseTimeTableDetails(student), courseSpiderExecutor);

            UrpCourseTimeTableForSpider tableForSpider = future.get(3000L, TimeUnit.MILLISECONDS);
            if (!hasSchoolCourse(tableForSpider)) {
                return transCourseTimeTableToVo(getCourseTimetableByClazz(student));
            } else {
                List<CourseTimetable> list = getCourseTimetableList(tableForSpider);
                saveCourseTimeTableDetailsFromSearch(list, student);
                return transCourseTimeTableToVo(list);
            }

        } catch (UrpRequestException | InterruptedException | ExecutionException | TimeoutException e) {
            return transCourseTimeTableToVo(getCourseTimetableByClazz(student));
        }
    }

    public List<CourseTimeTableVo> getCourseTimeTableByTeacherAccount(String account) {
        Teacher teacher = teacherDao.selectByAccount(account);
        return getCourseTimeTableByTeacher(teacher.getId());
    }

    public List<CourseTimeTableVo> getCourseTimetableVoByClazz(String classNum) {
        UrpClass urpClass = urpClassDao.selectByClassNumber(classNum);
        return transCourseTimeTableToVo(getCourseTimetableByClazz(urpClass));
    }

    public List<CourseTimeTableVo> getCourseTimeTableByTeacher(Integer teacherId) {
        List<Integer> idList = teacherCourseTimeTableDao.selectByPojo(new TeacherCourseTimetable().setTeacherId(teacherId))
                .stream()
                .map(TeacherCourseTimetable::getCourseTimetableId)
                .collect(Collectors.toList());

        if (idList.isEmpty()) {
            return Collections.emptyList();
        }
        return transCourseTimeTableToVo(courseTimeTableDao.selectByIdList(idList));
    }

    public List<CourseTimeTableVo> getCourseTimeTableByClassroom(String roomId) {
        List<CourseTimetable> courseTimetableList = courseTimeTableDao.selectByCourseTimetable(new CourseTimetable().setRoomNumber(roomId));
        return transCourseTimeTableToVo(courseTimetableList);
    }

    public List<CourseTimeTableVo> getCourseTimeTableByCourse(String courseId, String courseOrder) {
        List<CourseTimetable> courseTimetableList = courseTimeTableDao.selectByCourseTimetable(new CourseTimetable().setCourseId(courseId).setCourseSequenceNumber(courseOrder));
        return transCourseTimeTableToVo(courseTimetableList);
    }

    List<CourseTimetable> getCourseTimetableList(UrpCourseTimeTableForSpider tableForSpider) {
        return tableForSpider.getDetails()
                .stream()
                .flatMap(x -> x.values().stream().map(UrpCourseTimeTable::adapterToCourseTimetable))
                .flatMap(Collection::stream)
                .peek(x -> {
                    List<UrpClassroom> urpClassroomList = urpClassRoomDao.selectByClassroom(new UrpClassroom().setName(x.getRoomName()));
                    x.setRoomNumber(urpClassroomList.stream().findFirst().orElse(new UrpClassroom()).getNumber());
                })
                .collect(Collectors.toList());
    }

    public List<CourseTimeTableVo> transCourseTimeTableToVo(List<CourseTimetable> courseTimetableList) {
        return courseTimetableList.stream().map(x ->
                new CourseTimeTableVo()
                        .setAttendClassTeacher(x.getAttendClassTeacher())
                        .setCampusName(x.getCampusName())
                        .setClassDay(x.getClassDay())
                        .setClassOrder(x.getClassOrder())
                        .setClassInSchoolWeek(x.getClassInSchoolWeek())
                        .setContinuingSession(x.getContinuingSession())
                        .setStartWeek(x.getStartWeek())
                        .setEndWeek(x.getEndWeek())
                        .setRoomName(x.getRoomName())
                        .setRoomNumber(x.getRoomNumber())
                        .setWeekDescription(x.getWeekDescription())
                        .setTermOrder(x.getTermOrder())
                        .setTermYear(x.getTermYear())
                        .setStudentCount(x.getStudentCount())
                        .setCourse(urpCourseService.getCurrentTermCourse(x.getCourseId(), x.getCourseSequenceNumber())))
                .collect(Collectors.toList());
    }

    /**
     * 判断爬虫的返回结果是否只有网课
     */
    private boolean hasSchoolCourse(UrpCourseTimeTableForSpider tableForSpider) {
        for (HashMap<String, UrpCourseTimeTable> table : tableForSpider.getDetails()) {
            for (Map.Entry<String, UrpCourseTimeTable> entry : table.entrySet()) {
                if (entry.getValue().getTimeAndPlaceList().size() != 0) {
                    return true;
                }
            }
        }
        return false;

    }


    UrpCourseTimeTableForSpider getCourseTimeTableDetails(Student student) {
        UrpCourseTimeTableForSpider spiderResult = newUrpSpiderService.getUrpCourseTimeTable(student);
//        saveToDbAsync(spiderResult, student);
        return spiderResult;
    }

    /**
     * 返回该学期当天的所有课程详情
     * 从数据库获取的数据为空时，会使用爬虫来爬取数据
     *
     * @param student 学生实体
     * @return 课程时间表详情
     */
    public List<CourseTimeTableDetail> getDetailsForCurrentDay(Student student) {
        SchoolTime schoolTime = DateUtils.getCurrentSchoolTime();
        List<Integer> detailIdList = getCourseTimeTableDetailIdByAccount(student.getAccount());
        if (detailIdList.isEmpty()) {
            UrpCourseTimeTableForSpider tableForSpider = getCourseTimeTableDetails(student);
            return getCurrentDayDataFromSpider(tableForSpider, schoolTime);
        } else {
            return courseTimeTableDetailDao.getCourseTimeTableDetailForCurrentDay(detailIdList, schoolTime);
        }

    }

    /**
     * 返回当前节的课程详情
     * 从数据库获取的数据为空时，会使用爬虫来爬取数据
     *
     * @param student 学生实体
     * @param section 结束
     * @return 课程时间表详情
     */
    public List<CourseTimeTableDetail> getDetailsForSection(Student student, int section) {
        SchoolTime schoolTime = DateUtils.getCurrentSchoolTime();
        List<Integer> detailIdList = getCourseTimeTableDetailIdByAccount(student.getAccount());
        if (detailIdList.isEmpty()) {
            try {
                UrpCourseTimeTableForSpider tableForSpider = getCourseTimeTableDetails(student);
                if (!hasSchoolCourse(tableForSpider)) {
                    List<CourseTimeTableDetail> timetable = getCourseTimetableByClass(student);
                    return filterBySection(timetable, schoolTime, section);
                }
                return getAppointSectionDataFromSpider(tableForSpider, schoolTime, section);
            } catch (UrpRequestException e) {
                if (e.getCode() == 404) {
                    List<CourseTimeTableDetail> timetable = getCourseTimetableByClass(student);
                    return filterBySection(timetable, schoolTime, section);
                } else {
                    throw e;
                }
            }


        } else {

            return courseTimeTableDetailDao.getCourseTimeTableDetailForSection(detailIdList, schoolTime, section);
        }
    }

    /**
     * 将爬虫返回的数据中用于显示的部分全部提取出来，提取的部分为当前学期的所有课程时间表
     *
     * @param spiderResult 爬虫返回的结果
     * @param schoolTime   当前学期等信息
     * @return 课程时间表详情
     */
    private List<CourseTimeTableDetail> getCurrentTermDataFromSpider(UrpCourseTimeTableForSpider spiderResult, SchoolTime schoolTime) {
        List<CourseTimeTableDetail> result = Lists.newArrayListWithCapacity(16);
        for (Map<String, UrpCourseTimeTable> map : spiderResult.getDetails()) {
            for (Map.Entry<String, UrpCourseTimeTable> entry : map.entrySet()) {
                UrpCourseTimeTable urpCourseTimeTable = entry.getValue();
                if (CollectionUtils.isEmpty(urpCourseTimeTable.getTimeAndPlaceList())) {
                    continue;
                }
                for (int i = 0, length = urpCourseTimeTable.getTimeAndPlaceList().size(); i < length; i++) {
                    TimeAndPlace timeAndPlace = urpCourseTimeTable.getTimeAndPlaceList().get(i);
                    //TimeAndPlace保存了上课的地点和时间，因为周数有时候存在多个的情况，如1-5, 8-16周等情况
                    //所以TimeAndPlace转换的CourseTimeTableDetail返回的是一个集合
                    List<CourseTimeTableDetail> details =
                            timeAndPlace.convertToCourseTimeTableDetail(urpCourseTimeTable.getCourseRelativeInfo(), urpCourseTimeTable.getAttendClassTeacher());
                    details.stream().filter(detail -> Objects.equals(detail.getTermYear(), schoolTime.getTerm().getTermYear()))
                            .filter(detail -> detail.getTermOrder() == schoolTime.getTerm().getOrder())
                            .forEach(result::add);
                }
            }
        }
        return result;
    }

    /**
     * 将爬虫返回的数据中用于显示的部分全部提取出来，提取的部分为当前学期当前周的所有课程时间表
     *
     * @param spiderResult 爬虫返回的结果
     * @param schoolTime   当前学期等信息
     * @return 课程时间表详情
     */
    private List<CourseTimeTableDetail> getCurrentWeekDataFromSpider(UrpCourseTimeTableForSpider spiderResult, SchoolTime schoolTime) {
        List<CourseTimeTableDetail> result = Lists.newArrayListWithCapacity(16);
        for (Map<String, UrpCourseTimeTable> map : spiderResult.getDetails()) {
            for (Map.Entry<String, UrpCourseTimeTable> entry : map.entrySet()) {
                UrpCourseTimeTable urpCourseTimeTable = entry.getValue();
                if (CollectionUtils.isEmpty(urpCourseTimeTable.getTimeAndPlaceList())) {
                    continue;
                }
                for (int i = 0, length = urpCourseTimeTable.getTimeAndPlaceList().size(); i < length; i++) {
                    TimeAndPlace timeAndPlace = urpCourseTimeTable.getTimeAndPlaceList().get(i);
                    //TimeAndPlace保存了上课的地点和时间，因为周数有时候存在多个的情况，如1-5, 8-16周等情况
                    //所以TimeAndPlace转换的CourseTimeTableDetail返回的是一个集合
                    List<CourseTimeTableDetail> details =
                            timeAndPlace.convertToCourseTimeTableDetail(urpCourseTimeTable.getCourseRelativeInfo(), urpCourseTimeTable.getAttendClassTeacher());
                    details.stream().filter(detail -> detail.isActiveWeek(schoolTime.getWeek()))
                            .filter(detail -> Objects.equals(detail.getTermYear(), schoolTime.getTerm().getTermYear()))
                            .filter(detail -> detail.getTermOrder() == schoolTime.getTerm().getOrder())
                            .forEach(result::add);
                }
            }
        }
        return result;
    }

    /**
     * 将爬虫返回的数据中用于显示的部分全部提取出来，提取的部分为当前学期当前周当天的所有课程时间表
     *
     * @param spiderResult 爬虫返回的结果
     * @param schoolTime   当前学期等信息
     * @return 课程时间表详情
     */
    private List<CourseTimeTableDetail> getCurrentDayDataFromSpider(UrpCourseTimeTableForSpider spiderResult, SchoolTime schoolTime) {
        List<CourseTimeTableDetail> result = Lists.newArrayListWithCapacity(5);
        for (Map<String, UrpCourseTimeTable> map : spiderResult.getDetails()) {
            for (Map.Entry<String, UrpCourseTimeTable> entry : map.entrySet()) {
                UrpCourseTimeTable urpCourseTimeTable = entry.getValue();
                if (CollectionUtils.isEmpty(urpCourseTimeTable.getTimeAndPlaceList())) {
                    continue;
                }
                for (int i = 0, length = urpCourseTimeTable.getTimeAndPlaceList().size(); i < length; i++) {
                    TimeAndPlace timeAndPlace = urpCourseTimeTable.getTimeAndPlaceList().get(i);
                    //TimeAndPlace保存了上课的地点和时间，因为周数有时候存在多个的情况，如1-5, 8-16周等情况
                    //所以TimeAndPlace转换的CourseTimeTableDetail返回的是一个集合
                    List<CourseTimeTableDetail> details =
                            timeAndPlace.convertToCourseTimeTableDetail(urpCourseTimeTable.getCourseRelativeInfo(), urpCourseTimeTable.getAttendClassTeacher());
                    details.stream().filter(detail -> detail.getDay() == schoolTime.getDay())
                            .filter(detail -> detail.isActiveWeek(schoolTime.getWeek()))
                            .filter(detail -> Objects.equals(detail.getTermYear(), schoolTime.getTerm().getTermYear()))
                            .filter(detail -> detail.getTermOrder() == schoolTime.getTerm().getOrder())
                            .forEach(result::add);
                }
            }
        }
        return result;
    }

    private List<CourseTimeTableDetail> getAppointSectionDataFromSpider(UrpCourseTimeTableForSpider spiderResult, SchoolTime schoolTime, int section) {
        List<CourseTimeTableDetail> result = Lists.newArrayListWithCapacity(1);
        for (Map<String, UrpCourseTimeTable> map : spiderResult.getDetails()) {
            for (Map.Entry<String, UrpCourseTimeTable> entry : map.entrySet()) {
                UrpCourseTimeTable urpCourseTimeTable = entry.getValue();
                if (CollectionUtils.isEmpty(urpCourseTimeTable.getTimeAndPlaceList())) {
                    continue;
                }
                for (int i = 0, length = urpCourseTimeTable.getTimeAndPlaceList().size(); i < length; i++) {
                    TimeAndPlace timeAndPlace = urpCourseTimeTable.getTimeAndPlaceList().get(i);
                    //TimeAndPlace保存了上课的地点和时间，因为周数有时候存在多个的情况，如1-5, 8-16周等情况
                    //所以TimeAndPlace转换的CourseTimeTableDetail返回的是一个集合
                    List<CourseTimeTableDetail> details =
                            timeAndPlace.convertToCourseTimeTableDetail(urpCourseTimeTable.getCourseRelativeInfo(), urpCourseTimeTable.getAttendClassTeacher());
                    result.addAll(filterBySection(details, schoolTime, section));
                }
            }
        }
        return result;
    }

    private List<CourseTimeTableDetail> filterBySection(List<CourseTimeTableDetail> courseTimeTableDetailList,
                                                        SchoolTime schoolTime, int section) {
        return courseTimeTableDetailList.stream()
                .filter(detail -> detail.getDay() == schoolTime.getDay())
                .filter(detail -> detail.isActiveWeek(schoolTime.getWeek()))
                .filter(detail -> Objects.equals(detail.getTermYear(), schoolTime.getTerm().getTermYear()))
                .filter(detail -> detail.getTermOrder() == schoolTime.getTerm().getOrder())
                .filter(detail -> detail.getOrder() == section)
                .collect(Collectors.toList());

    }

    private void saveCourseTimeTableToDb(UrpCourseTimeTableForSpider spiderResult, Student student) {
        for (Map<String, UrpCourseTimeTable> map : spiderResult.getDetails()) {
            for (Map.Entry<String, UrpCourseTimeTable> entry : map.entrySet()) {
                UrpCourseTimeTable urpCourseTimeTable = entry.getValue();

                if (!urpCourseTimeTable.getCourseRelativeInfo().getStudentNumber().equals(String.valueOf(student.getAccount()))) {
                    return;
                }
                String courseId = urpCourseTimeTable.getCourseRelativeInfo().getCourseNumber();
                //查看课程是否存在，不存在就插入数据库
                urpCourseService.checkOrSaveUrpCourseToDb(courseId, student);
//                urpCourseService.saveCourse(courseId, sequenceNumber);
                CourseTimeTableBasicInfo basicInfo = getCourseTimeTableBasicInfo(urpCourseTimeTable);
                //将课程时间表存储到数据库
                saveCourseTimeTableDetailsToDb(urpCourseTimeTable, basicInfo, student);
            }
        }
    }

    /**
     * 将课程搜索结果保存到数据库中
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveCourseTimeTableDetailsToDb(UrpCourseTimeTable urpCourseTimeTable, CourseTimeTableBasicInfo basicInfo, Student student) {
        if (CollectionUtils.isEmpty(urpCourseTimeTable.getTimeAndPlaceList())) {
            return;
        }
        for (int i = 0, length = urpCourseTimeTable.getTimeAndPlaceList().size(); i < length; i++) {
            TimeAndPlace timeAndPlace = urpCourseTimeTable.getTimeAndPlaceList().get(i);
            //TimeAndPlace保存了上课的地点和时间，因为周数有时候存在多个的情况，如1-5, 8-16周等情况
            //所以TimeAndPlace转换的CourseTimeTableDetail返回的是一个集合
            List<CourseTimeTableDetail> detailList =
                    timeAndPlace.convertToCourseTimeTableDetail(urpCourseTimeTable.getCourseRelativeInfo(), urpCourseTimeTable.getAttendClassTeacher());

            Set<CourseTimeTableDetail> detailHashSet = new HashSet<>(detailList);
            if (detailHashSet.size() != detailList.size()) {
                log.info("有重复数据 {}", student.getClasses());
            }

            List<Integer> idList = Lists.newArrayList();
            List<CourseTimeTableDetail> needInsertDetailList = Lists.newArrayList();

            for (CourseTimeTableDetail detail : detailHashSet) {
                List<CourseTimeTableDetail> dbResult = courseTimeTableDetailDao.selectByDetail(detail);
                if (dbResult.size() == 0) {
                    needInsertDetailList.add(detail);
                } else if (dbResult.size() == 1) {
                    idList.add(dbResult.get(0).getId());
                } else {
                    List<Integer> list = dbResult.stream().map(CourseTimeTableDetail::getId).collect(Collectors.toList());

                    log.error("数据库重复课程信息 size:{}  id:{}", dbResult.size(), list.toString());
                }
            }

            if (!CollectionUtils.isEmpty(needInsertDetailList)) {
                List<Integer> list = saveTimeTableDetail(needInsertDetailList, timeAndPlace, basicInfo);
                log.info("class {} 插入detail size:{}  id:{}", student.getClasses(), list.size(), list.toString());
                idList.addAll(list);
            }

            //关联班级和课程详情
            if (!CollectionUtils.isEmpty(idList)) {

                saveClassAndDetailRelative(idList, student, basicInfo.getTermYear(), basicInfo.getTermOrder());
            }
        }

    }

    private void saveRelative(List<Integer> needInsertIds, Student student, String termYear, Integer termOrder) {
        for (Integer id : needInsertIds) {
            studentCourseTimeTableDao.insertSelective(new StudentCourseTimeTable()
                    .setCourseTimetableId(id)
                    .setStudentId(student.getAccount())
                    .setTermYear(termYear)
                    .setTermOrder(termOrder)
            );
        }
    }

    /**
     * 将课程搜索结果保存到数据库中，数据来源是不包含学生信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveCourseTimeTableDetailsFromSearch(List<CourseTimetable> courseTimetableList, Student student) {
        if (courseTimetableList.isEmpty()) {
            return;
        }
        String termYear = courseTimetableList.get(0).getTermYear();
        Integer termOrder = courseTimetableList.get(0).getTermOrder();

        List<Integer> idList = getCourseTimetableIdList(courseTimetableList);
        //关联班级和课程详情
        if (!CollectionUtils.isEmpty(idList)) {
            saveRelative(idList, student, termYear, termOrder);
        }
    }

    List<Integer> getCourseTimetableIdList(List<CourseTimetable> courseTimetableList) {
        List<Integer> idList = Lists.newArrayList();
        for (CourseTimetable courseTimetable : courseTimetableList) {
            CourseTimetable course = courseTimeTableDao.selectUniqueCourse(courseTimetable);

            // 如果没有这个课程的上课信息，用搜索功能去抓取
            // 理论上应该都有  没有的话可能是程序有问题或者是教务网的数据缺失  都应该仔细检查一下
            if (course == null) {
                courseTimeTableDao.insertSelective(courseTimetable);
                log.info("insert course info {}", courseTimetable);
                idList.add(courseTimetable.getId());
            } else {
                if (!course.equals(courseTimetable)) {
                    courseTimeTableDao.updateByUniqueKey(courseTimetable);
                    log.info("courseTimetable origin {}\nupdate {}",course, courseTimetable);
                }
                idList.add(course.getId());
            }
        }

        return idList;
    }

    private void saveClassAndDetailRelative(List<Integer> needInsertIds, Student student, String termYear, Integer termOrder) {
        courseTimeTableDetailDao.insertStudentCourseTimeTableBatch(needInsertIds, student.getAccount(), termYear, termOrder);
    }

    private List<Integer> saveTimeTableDetail(List<CourseTimeTableDetail> needInsertDetails, TimeAndPlace timeAndPlace, CourseTimeTableBasicInfo basicInfo) throws RoomParseException {
        for (CourseTimeTableDetail detail : needInsertDetails) {
            detail.setCourseTimeTableBasicInfoId(basicInfo.getId());
            courseTimeTableDetailDao.insertCourseTimeTableDetail(detail);
            Room parseResult = roomService.parseToRoomForSpider(timeAndPlace.getClassroomName(), timeAndPlace.getTeachingBuildingName());
            roomDao.saveOrGetRoomFromDb(parseResult);
        }
        return needInsertDetails.stream().map(CourseTimeTableDetail::getId).collect(Collectors.toList());

    }

    private CourseTimeTableBasicInfo getCourseTimeTableBasicInfo(UrpCourseTimeTable urpCourseTimeTable) {
        Plan plan = getPlan(urpCourseTimeTable);
        CourseTimeTableBasicInfo basicInfoSpiderResult = urpCourseTimeTable.convertToCourseTimeTableBasicInfo();
        basicInfoSpiderResult.setPlanId(plan.getId());
        return courseTimeTableBasicInfoDao.saveOrGetCourseTimeTableBasicInfoFromDb(basicInfoSpiderResult);
    }

    private Plan getPlan(UrpCourseTimeTable urpCourseTimeTable) {
        return planDao.saveOrGetPlanFromDb(urpCourseTimeTable.convertToPlan());
    }

    /**
     * 计算节数
     *
     * @param detail 课程时间表详情
     * @return 节数字符串
     */
    private String computationOfKnots(CourseTimeTableDetail detail) {
        return detail.getOrder() + "-" + (detail.getOrder() + detail.getContinuingSession() - 1);
    }

    private List<CourseTimeTableDetailDto> assembleCourseTimeTableDto(List<CourseTimeTableDetail> details) {
        return details.stream().map(detail -> {
            CourseTimeTableDetailDto detailVo = new CourseTimeTableDetailDto();
            detailVo.setDetail(detail);

            Course course = urpCourseService.getCurrentTermCourse(detail.getCourseId(), detail.getCourseSequenceNumber());
            UrpCourse urpCourse = new UrpCourse();
            urpCourse.setCourseName(course.getName());
            detailVo.setUrpCourse(urpCourse);
            return detailVo;
        }).collect(Collectors.toList());
    }

    private List<Integer> getCourseTimeTableDetailIdByAccount(Integer account) {
        SchoolTime schoolTime = DateUtils.getCurrentSchoolTime();

        StudentCourseTable table = new StudentCourseTable()
                .setAccount(account)
                .setTermOrder(schoolTime.getTerm().getOrder())
                .setTermYear(schoolTime.getTerm().getTermYear());
        List<StudentCourseTable> tableList = courseTimeTableDetailDao.selectStudentCourseTimeTableRelative(table);
        return tableList.stream().map(StudentCourseTable::getCourseTimeTableId).collect(Collectors.toList());
    }

    private List<Integer> getCourseTimeTableIdByAccount(Integer account) {
        SchoolTime schoolTime = DateUtils.getCurrentSchoolTime();

        StudentCourseTimeTable table = new StudentCourseTimeTable()
                .setStudentId(account)
                .setTermOrder(schoolTime.getTerm().getOrder())
                .setTermYear(schoolTime.getTerm().getTermYear());
        List<StudentCourseTimeTable> tableList = studentCourseTimeTableDao.selectByExample(table);
        return tableList.stream().map(StudentCourseTimeTable::getCourseTimetableId).collect(Collectors.toList());
    }

    private List<CourseTimeTableDetail> getCourseTimetableByClass(Student student) {
        UrpClass urpClass = classService.getUrpClassByStudent(student);

        if(urpClass == null){
            return Collections.emptyList();
        }

        return classCourseTimetableDao.selectByPojo(new ClassCourseTimetable().setClassId(urpClass.getClassNum()))
                .stream()
                .map(ClassCourseTimetable::getCourseTimetableId)
                .map(id -> {
                    CourseTimetable timetable = courseTimeTableDao.selectByPrimaryKey(id);
                    return new CourseTimeTableDetail()
                            .setOrder(timetable.getClassOrder())
                            .setDay(timetable.getClassDay())
                            .setCourseId(timetable.getCourseId())
                            .setCourseSequenceNumber(timetable.getCourseSequenceNumber())
                            .setAttendClassTeacher(timetable.getAttendClassTeacher())
                            .setCampusName(timetable.getCampusName())
                            .setStartWeek(timetable.getStartWeek())
                            .setEndWeek(timetable.getEndWeek())
                            .setWeekDescription(timetable.getWeekDescription())
                            .setWeek(timetable.getClassInSchoolWeek())
                            .setTermYear(timetable.getTermYear())
                            .setTermOrder(timetable.getTermOrder())
                            .setRoomName(timetable.getRoomName());
                })
                .collect(Collectors.toList());
    }

    private List<CourseTimetable> getCourseTimetableByClazz(Student student) {
        UrpClass urpClass = classService.getUrpClassByStudent(student);

        if(urpClass == null){
            return Collections.emptyList();
        }

        return getCourseTimetableByClazz(urpClass);
    }


    private List<CourseTimetable> getCourseTimetableByClazz(UrpClass urpClass) {
        return classCourseTimetableDao.selectByPojo(new ClassCourseTimetable().setClassId(urpClass.getClassNum()))
                .stream()
                .map(x -> courseTimeTableDao.selectByPrimaryKey(x.getCourseTimetableId()))
                .collect(Collectors.toList());
    }

}
