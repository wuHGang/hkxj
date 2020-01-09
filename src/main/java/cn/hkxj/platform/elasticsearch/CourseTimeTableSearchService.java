package cn.hkxj.platform.elasticsearch;

import cn.hkxj.platform.dao.*;
import cn.hkxj.platform.elasticsearch.document.CourseTimeTableDocument;
import cn.hkxj.platform.mapper.TeacherMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.vo.CourseTimetableSearchResultVo;
import cn.hkxj.platform.service.UrpCourseService;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseTimeTableSearchService {
    @Resource
    private CourseTimeTableRepository courseTimeTableRepository;
    @Resource
    private CourseTimeTableDao courseTimeTableDao;
    @Resource
    private TeacherCourseTimeTableDao teacherCourseTimeTableDao;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private CourseDao courseDao;
    @Resource
    private ClassCourseTimetableDao classCourseTimetableDao;
    @Resource
    private UrpClassDao urpClassDao;
    @Resource
    private UrpClassRoomDao urpClassRoomDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private UrpCourseService urpCourseService;

    private String termYear = "2019-2020";

    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }


    public void saveTimeTableDate() {

        CourseTimetable courseTimetable = new CourseTimetable()
                .setTermYear(termYear)
                .setTermOrder(1);
        ArrayList<CourseTimeTableDocument> documents = new ArrayList<>();
        for (CourseTimetable timetable : courseTimeTableDao.selectByCourseTimetable(courseTimetable)) {
            List<Teacher> teacherList = getTeacherByTable(timetable);
            Course course = getCourseByTable(timetable);
            List<UrpClass> urpClassList = getClassByTable(timetable);

            CourseTimeTableDocument courseTimeTableDocument = new CourseTimeTableDocument()
                    .setId((long) timetable.getId())
                    .setCourseName(course.getName())
                    .setCourseId(timetable.getCourseId())
                    .setCourseOrder(timetable.getCourseSequenceNumber())
                    .setAcademyName(course.getAcademyName())
                    .setClassDay(timetable.getClassDay())
                    .setClassOrder(timetable.getClassOrder())
                    .setEndWeek(timetable.getEndWeek())
                    .setStartWeek(timetable.getStartWeek())
                    .setClassRoomName(timetable.getRoomName())
                    .setClassRoomNumber(timetable.getRoomNumber())
                    .setWeekDescription(timetable.getWeekDescription())
                    .setTeacherNameList(teacherList.stream().map(Teacher::getName).collect(Collectors.toList()))
                    .setTeacherAccountList(teacherList.stream().map(Teacher::getAccount).collect(Collectors.toList()))
                    .setClassNameList(urpClassList.stream().map(UrpClass::getClassName).collect(Collectors.toList()))
                    .setClassNumList(urpClassList.stream().map(UrpClass::getClassNum).collect(Collectors.toList()))
                    .setSubjectNameList(new ArrayList<>(urpClassList.stream().map(x -> x.getAdmissionGrade() + x.getSubjectName()).collect(Collectors.toSet())))
                    .setTermYear(timetable.getTermYear())
                    .setTermOrder(timetable.getTermOrder());
            documents.add(courseTimeTableDocument);
            CourseTimeTableDocument save = courseTimeTableRepository.save(courseTimeTableDocument);
            System.out.println(save);
        }


    }

    public List<CourseTimetableSearchResultVo> searchCourseTimeTableV2(int page, int size, String query) {
        return searchCourseTimeTable(page, size, query).stream()
                .map(x -> new CourseTimetableSearchResultVo()
                        .setAcademyName(x.getAcademyName())
                        .setClassDay(x.getClassDay())
                        .setClassOrder(x.getClassOrder())
                        .setClassroom(urpClassRoomDao.selectByNumber(x.getClassRoomNumber()))
                        .setStartWeek(x.getStartWeek())
                        .setEndWeek(x.getEndWeek())
                        .setUrpClassList(x.getClassNumList()
                                .stream()
                                .map(num -> urpClassDao.selectByClassNumber(num))
                                .sorted(Comparator.comparing(UrpClass::getClassNum))
                                .collect(Collectors.toList())
                        )
                        .setCourse(urpCourseService.getCurrentTermCourse(x.getCourseId(), x.getCourseOrder()))
                        .setTeacherList(x.getTeacherAccountList()
                                .stream()
                                .map(account -> teacherDao.selectByAccount(account))
                                .collect(Collectors.toList())
                        )
                        .setWeekDescription(x.getWeekDescription())
                        .setSubjectNameList(x.getSubjectNameList())
                        .setTermYear(x.getTermYear())
                        .setTermOrder(x.getTermOrder()))
                .collect(Collectors.toList());

    }

    public List<CourseTimeTableDocument> searchCourseTimeTable(int page, int size, String query) {

        // 分页参数
        PageRequest pageRequest = PageRequest.of(page, size);
        DisMaxQueryBuilder builder1 = QueryBuilders.disMaxQuery()
                .add(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("courseName", query))
                        .should(QueryBuilders.matchQuery("teacherNameList", query))
                        .should(QueryBuilders.matchQuery("classNameList", query))
                        .should(QueryBuilders.matchQuery("subjectNameList", query))
                        .should(QueryBuilders.matchQuery("classRoomName", query)));

        DisMaxQueryBuilder builder = QueryBuilders.disMaxQuery()
                .add(QueryBuilders.matchQuery("courseName", query))
                .add(QueryBuilders.matchQuery("teacherNameList", query))
                .add(QueryBuilders.matchQuery("classNameList", query))
                .add(QueryBuilders.matchQuery("subjectNameList", query))
                .add(QueryBuilders.matchQuery("classRoomName", query));


        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(pageRequest)
                .build();

        return courseTimeTableRepository.search(searchQuery).stream().collect(Collectors.toList());
    }

    private List<Teacher> getTeacherByTable(CourseTimetable timetable) {
        List<Teacher> teacherList = new ArrayList<>();
        for (TeacherCourseTimetable teacherCourseTimetable : teacherCourseTimeTableDao.selectByPojo(new TeacherCourseTimetable()
                .setCourseTimetableId(timetable.getId())
                .setTermYear(termYear)
                .setTermOrder(1)
        )) {
            teacherList.add(teacherMapper.selectByPrimaryKey(teacherCourseTimetable.getTeacherId()));
        }
        return teacherList;
    }


    private List<UrpClass> getClassByTable(CourseTimetable timetable) {
        List<UrpClass> urpClassList = new ArrayList<>();
        for (ClassCourseTimetable courseTimetable : classCourseTimetableDao.selectByPojo(new ClassCourseTimetable()
                .setTermYear(termYear)
                .setTermOrder(1)
                .setCourseTimetableId(timetable.getId()))) {
            urpClassList.add(urpClassDao.selectByClassNumber(courseTimetable.getClassId()));
        }

        return urpClassList;
    }

    private Course getCourseByTable(CourseTimetable timetable) {
        List<Course> courseList = courseDao.selectCourseByPojo(new Course()
                .setNum(timetable.getCourseId())
                .setCourseOrder(timetable.getCourseSequenceNumber())
                .setTermYear(termYear)
                .setTermOrder(1)
        );
        if (courseList.size() != 1) {
            System.out.println(courseList);
        }
        return courseList.get(0);
    }
}
