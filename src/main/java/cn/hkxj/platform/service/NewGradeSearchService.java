package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.newmodel.grade.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.grade.detail.UrpGradeDetailForSpider;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGeneralGradeForSpider;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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

    private static final Term currentTerm = new Term(2019, 2020, 1);

    private static DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

    public GradeSearchResult getCurrentGrade(Student student) {
        CurrentGrade currentGrade = newUrpSpiderService.getCurrentTermGrade(student.getAccount().toString(),
                student.getPassword());

        if(CollectionUtils.isEmpty(currentGrade.getList())){
            return new GradeSearchResult(Lists.newArrayListWithCapacity(0), false);
        }


        return saveCurrentGradeToDb(student, currentGrade);
    }

    public GradeSearchResult getCurrentGrade(String account, String password) {
        Student student = Optional.ofNullable(studentDao.selectStudentByAccount(Integer.parseInt(account)))
                .orElseGet(() ->newUrpSpiderService.getStudentInfo(account, password));

        return getCurrentGrade(student);
    }


    /**
     * 生成一个UrpGradeAndUrpCourse
     * @param courseId 课程号
     * @param term 学期
     * @param urpGrade 成绩实体
     * @param urpGradeDetailList 成绩详情实体
     * @return UrpGradeAndUrpCourse
     */
    private UrpGradeAndUrpCourse generateUrpGradeAndUrpCourse(String courseId, Term term, UrpGrade urpGrade, List<UrpGradeDetail> urpGradeDetailList){
        UrpGradeAndUrpCourse target = new UrpGradeAndUrpCourse();
        NewGrade newGrade = new NewGrade();
        newGrade.setDetails(urpGradeDetailList);
        newGrade.setUrpGrade(urpGrade);
        target.setNewGrade(newGrade);
        target.setTerm(term);
        target.setUrpCourse(urpCourseService.getUrpCourseByCourseId(courseId));
        return target;
    }

    /**
     * 检查爬虫结果中是否需要存储的同时，返回包含着所有要显示的内容的集合
     * @param student 学生实体
     * @param currentGrade 从爬虫爬取的当前学期的成绩
     * @return 所有内容
     */
    private List<UrpGradeAndUrpCourse> getUrpGradeAndUrpCourse(Student student, CurrentGrade currentGrade){
        return currentGrade.getList().stream().map(urpGradeForSpider -> {
            UrpGeneralGradeForSpider generalGradeForSpider = urpGradeForSpider.getUrpGeneralGradeForSpider();
            String uid = generalGradeForSpider.getId().getCourseNumber();
            //判断对应的课程是否存在，不存在就从爬虫获取后保存到数据库
            urpCourseService.checkOrSaveUrpCourseToDb(uid, student);
            //对教学计划相关的信息进行判断是否需要保存
            Plan plan = getPlan(generalGradeForSpider);
            //对专业相关的信息进行判断是否需要保存
            Major major = getMajor(generalGradeForSpider);
            UrpExam urpExam = getUrpExam(generalGradeForSpider, plan, major, student);
            UrpGrade urpGrade = getUrpGrade(generalGradeForSpider, urpExam, student);
            List<UrpGradeDetail> urpGradeDetailList = getUrpGradeDetail(urpGradeForSpider.getUrpGradeDetailForSpider(), urpGrade);
            return generateUrpGradeAndUrpCourse(uid, currentTerm, urpGrade, urpGradeDetailList);
        }).collect(Collectors.toList());

    }

    private GradeSearchResult saveCurrentGradeToDb(Student student, CurrentGrade currentGrade){
        GradeSearchResult result = new GradeSearchResult();
        int beforeUpdateCount = urpGradeDao.getTotalCount();
        List<UrpGradeAndUrpCourse> allContent = getUrpGradeAndUrpCourse(student, currentGrade);
        result.setData(allContent);
        result.setUpdate(allContent.size() - beforeUpdateCount > 0);
        return result;
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
                if(Objects.isNull(grade)) continue;
                buffer.append("考试名称：").append(urpGradeAndUrpCourse.getUrpCourse().getCourseName()).append("\n")
                        .append("成绩：").append(grade == -1 ? "" : decimalFormat.format(grade)).append("   学分：")
                        .append((decimalFormat.format(urpGradeAndUrpCourse.getUrpCourse().getCredit()))).append("\n\n");
            }
            buffer.append("- - - - - - - - - - - - - -");
        }
        return buffer.toString();
    }


    private Plan getPlan(UrpGeneralGradeForSpider generalGradeForSpider){
        return planDao.saveOrGetPlanFromDb(generalGradeForSpider.convertToPlan());
    }

    private Major getMajor(UrpGeneralGradeForSpider generalGradeForSpider){
        return majorDao.saveOrGetMajorFromDb(generalGradeForSpider.convertToMajor(), generalGradeForSpider.getZyh());
    }

    private UrpExam getUrpExam(UrpGeneralGradeForSpider generalGradeForSpider, Plan plan, Major major, Student student){
        UrpExam urpExamFromSpider = generalGradeForSpider.convertToUrpExam();
        urpExamFromSpider.setPlanId(plan.getId());
        urpExamFromSpider.setMajorId(major.getId());
        urpExamFromSpider.setClassId(student.getClasses().getId());
        //判断对应的考试是否存在
        return urpExamDao.saveOrGetUrpExamFromDb(urpExamFromSpider, currentTerm);
    }

    private UrpGrade getUrpGrade(UrpGeneralGradeForSpider generalGradeForSpider, UrpExam urpExam, Student student){
        UrpGrade urpGradeFromSpider = generalGradeForSpider.convertToUrpGrade();
        urpGradeFromSpider.setExamId(urpExam.getId());
        urpGradeFromSpider.setAccount(student.getAccount());
        return urpGradeDao.saveOrGetUrpGradeFromDb(urpGradeFromSpider);
    }

    private List<UrpGradeDetail> getUrpGradeDetail(UrpGradeDetailForSpider gradeDetailForSpider, UrpGrade urpGrade){
        List<UrpGradeDetail> urpGradeDetailListFromSpider = gradeDetailForSpider.convertToUrpGradeDetail();
        return urpGradeDetailDao.saveOrGetUrpGradeDetailFromDb(urpGradeDetailListFromSpider, urpGrade.getId());
    }
}
