package cn.hkxj.platform.service;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.dao.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.vo.GradeDetailVo;
import cn.hkxj.platform.pojo.vo.GradeVo;
import cn.hkxj.platform.spider.newmodel.grade.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.grade.detail.GradeDetailSearchPost;
import cn.hkxj.platform.spider.newmodel.grade.detail.UrpGradeDetailForSpider;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGeneralGradeForSpider;
import cn.hkxj.platform.utils.DateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/8/1 17:05
 */
@Slf4j
@Service
public class NewGradeSearchService {

    @Resource
    private PlanDao planDao;
    @Resource
    private MajorDao majorDao;
    @Resource
    private UrpExamDao urpExamDao;
    @Resource
    private UrpGradeDao urpGradeDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private UrpCourseService urpCourseService;
    @Resource
    private UrpGradeDetailDao urpGradeDetailDao;
    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private GradeDao gradeDao;


    private static final Term currentTerm = new Term(2019, 2020, 1);

    private static DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

    private static ExecutorService gradeAutoUpdatePool = new MDCThreadPool(8, 8,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "gradeSearch"));

    /**
     * 这个方法已经过时，冗余出来给还没更新小程序新版本的用户使用
     * 请使用新方法{@linkplain #getCurrentTermGrade}
     */
    @Deprecated
    public GradeSearchResult getCurrentGrade(Student student) {
        List<GradeVo> currentTermGrade = getCurrentTermGrade(student);
        List<UrpGradeAndUrpCourse> result = currentTermGrade.stream()
                .map(x -> new UrpGradeAndUrpCourse()
                        .setNewGrade(new NewGrade().setUrpGrade(new UrpGrade().setScore(x.getScore())))
                        .setUrpCourse(new UrpCourse().setCourseName(x.getCourse().getName())
                                .setCredit(Double.parseDouble(x.getCourse().getCredit()))
                        )
                        .setTerm(DateUtils.getCurrentSchoolTime().getTerm())

                ).collect(Collectors.toList());

        return new GradeSearchResult(result, false);
    }

    public List<GradeDetail> getCurrentTermGradeFromSpider(Student student) {
        List<UrpGeneralGradeForSpider> generalGrade = newUrpSpiderService.getCurrentGeneralGrade(student);
        generalGrade.stream().findFirst().ifPresent(x -> {
            if (!x.getId().getStudentNumber().equals(student.getAccount().toString())) {
                log.error("account {} grade error data {}", student.getAccount(), generalGrade);
            }
        });

        return generalGrade.stream().map(urpGradeForSpider -> {

            // TODO 将其它对象重新整理入库
            //对教学计划相关的信息进行判断是否需要保存
//            Plan plan = getPlan(generalGradeForSpider);
            //对专业相关的信息进行判断是否需要保存
//            Major major = getMajor(generalGradeForSpider);
//            UrpExam urpExam = getUrpExam(generalGradeForSpider, plan, major, student);
            Grade grade = urpGradeForSpider.convertToGrade();


            return new GradeDetail(grade, null);

        }).collect(Collectors.toList());

    }


    public List<GradeVo> getCurrentTermGrade(String account, String password) {
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));
        return getCurrentTermGrade(student);
    }

    /**
     * 这个方法是提供给前端使用，当抓取超时或者错误得时候会从数据库中读取数据，能保证一个有返回
     *
     * @param student 学生实体
     * @return 学生成绩
     */
    public List<GradeVo> getCurrentTermGrade(Student student) {
        CompletableFuture<List<GradeDetail>> future =
                CompletableFuture.supplyAsync(() -> getCurrentTermGradeFromSpider(student), gradeAutoUpdatePool);
        List<GradeDetail> gradeDetailList;
        try {
            gradeDetailList = future.get(5000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            return gradeToVo(gradeDao.getCurrentTermGradeByAccount(student.getAccount()));
        }

        List<Grade> gradeList = gradeDetailList.stream().map(GradeDetail::getGrade).collect(Collectors.toList());
        // 检查哪些是新的成绩数据
        List<Grade> updateList = checkUpdate(student, gradeList);
        // 新的数据插入，原有得数据更新
        saveUpdateGrade(updateList);

        return gradeToVo(gradeList);
    }


    /**
     * 这个方法主要得主要目的是为了提供给关注抓取错误，而对返回结果时间不敏感的调用方使用
     *
     * @param student 学生实体
     * @return 学生成绩
     */
    public List<GradeVo> getCurrentTermGradeSync(Student student) {
        List<GradeDetail> gradeDetailList = getCurrentTermGradeFromSpider(student);

        List<Grade> gradeList = gradeDetailList.stream().map(GradeDetail::getGrade).collect(Collectors.toList());
        // 检查哪些是新的成绩数据
        List<Grade> updateList = checkUpdate(student, gradeList);
        // 新的数据插入，原有得数据更新
        saveUpdateGrade(updateList);


        return gradeToVo(gradeList);
    }


    public List<GradeVo> gradeToVo(List<Grade> gradeList) {
        return gradeList.stream().map(x ->
        {
            Course course = new Course();
            course.setCredit(x.getCredit().toString());
            course.setExamType(x.getExamTypeName());
            course.setExamTypeCode(x.getExamTypeCode());
            course.setCourseOrder(x.getCourseOrder());
            return new GradeVo()
                    .setCourse(urpCourseService.getCurrentTermCourse(x.getCourseNumber(), x.getCourseOrder(),
                            course))
                    .setAccount(x.getAccount())
                    .setScore(x.getScore())
                    .setGradePoint(x.getGradePoint())
                    .setLevelName(x.getLevelName())
                    .setLevelPoint(x.getLevelPoint())
                    .setRank(x.getRank())
                    .setReplaceCourseNumber(x.getReplaceCourseNumber())
                    .setRemark(x.getRemark())
                    .setRetakeCourseMark(x.getRetakeCourseMark())
                    .setRetakecourseModeCode(x.getRetakecourseModeCode())
                    .setRetakeCourseModeExplain(x.getRetakeCourseModeExplain())
                    .setUnpassedReasonCode(x.getUnpassedReasonCode())
                    .setUnpassedReasonExplain(x.getUnpassedReasonExplain())
                    .setStandardPoint(x.getStandardPoint())
                    .setTermYear(x.getTermYear())
                    .setTermOrder(x.getTermOrder())
                    .setUpdate(x.isUpdate())
                    .setExamTime(DateUtils.localDateToDate(x.getExamTime(), DateUtils.YYYY_MM_DD_PATTERN))
                    .setExamTimeStr(x.getExamTime())
                    .setOperateTime(DateUtils.localDateToDate(x.getOperateTime(), DateUtils.PATTERN_WITHOUT_SPILT))
                    .setOperator(x.getOperator());
        })

                .sorted(Comparator.comparing(GradeVo::getOperateTime).reversed())
                .collect(Collectors.toList());

    }

    /**
     * 返回新一个新成绩的list，旧的的成绩会被过滤
     */
    public List<Grade> checkUpdate(Student student, List<Grade> gradeList) {
        List<Grade> gradeListFromDb = gradeDao.getCurrentTermGradeByAccount(student.getAccount());
        if (CollectionUtils.isEmpty(gradeListFromDb)) {
            return gradeList;
        }

        Map<String, Grade> gradeMap = gradeListFromDb.stream().collect(Collectors.toMap(x -> x.getCourseNumber() + x.getCourseOrder(), x -> x));
        return gradeList.stream()
                .filter(grade -> {
                    Grade checkGrade = gradeMap.get(grade.getCourseNumber() + grade.getCourseOrder());

                    if (checkGrade == null) {
                        return true;
                    }
                    boolean update = !Objects.equals(grade, checkGrade);

                    grade.setId(checkGrade.getId());
                    grade.setUpdate(update);

                    return update;
                }).collect(Collectors.toList());
    }


    public void saveUpdateGrade(List<Grade> gradeList) {
        for (Grade grade : gradeList) {
            if (grade.getId() != null) {
                gradeDao.updateByPrimaryKeySelective(grade);
            } else {
                gradeDao.insertSelective(grade);
            }
        }


    }

    public GradeSearchResult getCurrentGrade(String account, String password) {
        Student student = Optional.ofNullable(studentDao.selectStudentByAccount(Integer.parseInt(account)))
                .orElseGet(() -> newUrpSpiderService.getStudentInfo(account, password));

        return getCurrentGrade(student);
    }

    public GradeDetailVo getGradeDetail(String account, GradeDetailSearchPost gradeDetailSearchPost) {

        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));

        UrpGradeDetailForSpider detail = newUrpSpiderService.getGradeDetail(student, gradeDetailSearchPost);

        Map<String, UrpGradeDetail> detailMap = detail.convertToUrpGradeDetail().stream().collect(Collectors.toMap(UrpGradeDetail::getScoreTypeCode, x -> x));

        GradeDetailVo vo = new GradeDetailVo();
        if (detailMap.containsKey("001")) {
            UrpGradeDetail urpGradeDetail = detailMap.get("001");
            vo.setClassGrade(new GradeDetailVo.GradeDetailVoItem(urpGradeDetail));
        }
        if (detailMap.containsKey("002")) {
            UrpGradeDetail urpGradeDetail = detailMap.get("002");
            vo.setExperimentGrade(new GradeDetailVo.GradeDetailVoItem(urpGradeDetail));
        }
        if (detailMap.containsKey("003")) {
            UrpGradeDetail urpGradeDetail = detailMap.get("003");
            vo.setPracticeGrade(new GradeDetailVo.GradeDetailVoItem(urpGradeDetail));
        }

        return vo;

    }

    public static String gradeListToText(List<UrpGradeAndUrpCourse> studentGrades) {
        StringBuilder buffer = new StringBuilder();
        if (studentGrades.size() == 0) {
            buffer.append("尚无本学期成绩");
        } else {
            //因为查询的都是同学期的，所以取第一个元素即可
            Term term = studentGrades.get(0).getTerm();
            buffer.append("- - - - - - - - - - - - - -\n");
            buffer.append("|").append(term.getTermCode()).append("学年，").append(term.getTermName()).append("|\n");
            for (UrpGradeAndUrpCourse urpGradeAndUrpCourse : studentGrades) {
                Double grade = urpGradeAndUrpCourse.getNewGrade().getUrpGrade().getScore();
                //如果分数为空，就直接跳过当前元素
                if (Objects.isNull(grade)) continue;
                buffer.append("考试名称：").append(urpGradeAndUrpCourse.getUrpCourse().getCourseName()).append("\n")
                        .append("成绩：").append(grade == -1 ? "" : decimalFormat.format(grade)).append("   学分：")
                        .append((decimalFormat.format(urpGradeAndUrpCourse.getUrpCourse().getCredit()))).append("\n\n");
            }
            buffer.append("- - - - - - - - - - - - - -");
        }
        return buffer.toString();
    }


    private Plan getPlan(UrpGeneralGradeForSpider generalGradeForSpider) {
        return planDao.saveOrGetPlanFromDb(generalGradeForSpider.convertToPlan());
    }

    private Major getMajor(UrpGeneralGradeForSpider generalGradeForSpider) {
        return majorDao.saveOrGetMajorFromDb(generalGradeForSpider.convertToMajor(), generalGradeForSpider.getZyh());
    }

    private UrpExam getUrpExam(UrpGeneralGradeForSpider generalGradeForSpider, Plan plan, Major major, Student student) {
        UrpExam urpExamFromSpider = generalGradeForSpider.convertToUrpExam();
        urpExamFromSpider.setPlanId(plan.getId());
        urpExamFromSpider.setMajorId(major.getId());
        urpExamFromSpider.setClassId(student.getClasses().getId());
        //判断对应的考试是否存在
        return urpExamDao.saveOrGetUrpExamFromDb(urpExamFromSpider, currentTerm);
    }

    private UrpGrade getUrpGrade(UrpGeneralGradeForSpider generalGradeForSpider, UrpExam urpExam, Student student) {
        UrpGrade urpGradeFromSpider = generalGradeForSpider.convertToUrpGrade();
        urpGradeFromSpider.setExamId(urpExam.getId());
        urpGradeFromSpider.setAccount(student.getAccount());
        return urpGradeDao.saveOrGetUrpGradeFromDb(urpGradeFromSpider);
    }

    private List<UrpGradeDetail> getUrpGradeDetail(UrpGradeDetailForSpider gradeDetailForSpider, UrpGrade urpGrade) {
        List<UrpGradeDetail> urpGradeDetailListFromSpider = gradeDetailForSpider.convertToUrpGradeDetail();
        return urpGradeDetailDao.saveOrGetUrpGradeDetailFromDb(urpGradeDetailListFromSpider, urpGrade.getId());
    }
}
