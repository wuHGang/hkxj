package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.newmodel.*;
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
    private UrpCourseDao urpCourseDao;
    @Resource
    private UrpGradeDetailDao urpGradeDetailDao;
    @Resource
    private NewUrpSpiderService newUrpSpiderService;

    private static final Term currentTerm = new Term(2018, 2019, 2);

    private static DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

    public GradeSearchResult getCurrentGrade(Student student) {
        CurrentGrade currentGrade = newUrpSpiderService.getCurrentTermGrade(student.getAccount().toString(),
                student.getPassword());

        if(CollectionUtils.isEmpty(currentGrade.getList())){
            return new GradeSearchResult(Lists.newArrayListWithCapacity(0), false);
        }


        return saveCurrentGradeToDb(student, currentGrade);
    }

    public List<UrpGradeAndUrpCourse> getUrpGradeAndUrpCourseFromDb(Student student) {
        Map<String, NewGrade> courseAndGrade = getGradeFromDb(student);
        List<UrpGradeAndUrpCourse> urpGradeAndUrpCourses = Lists.newArrayList();
        courseAndGrade.forEach((courseId, newGrade) -> {
            UrpCourse urpCourse = urpCourseDao.getUrpCourseByUid(courseId);
            UrpGradeAndUrpCourse urpGradeAndUrpCourse = new UrpGradeAndUrpCourse();
            urpGradeAndUrpCourse.setNewGrade(newGrade);
            urpGradeAndUrpCourse.setUrpCourse(urpCourse);
            urpGradeAndUrpCourse.setTerm(currentTerm);
            urpGradeAndUrpCourses.add(urpGradeAndUrpCourse);
        });
        return urpGradeAndUrpCourses;
    }

    private Map<String, NewGrade> getGradeFromDb(Student student) {
        List<UrpExam> urpExamList = urpExamDao.getOneClassCurrentTermAllUrpExam(student.getClasses().getId(), currentTerm);
        List<UrpGrade> urpGradeList = urpGradeDao.getCurrentTermAllUrpGrade(student.getAccount(),
                urpExamList.stream().map(UrpExam::getId).collect(Collectors.toList()));
        Map<String, NewGrade> results = Maps.newHashMap();
        for (int i = 0, length = urpExamList.size(); i < length; i++) {
            UrpExam exam = urpExamList.get(i);
            UrpGrade grade = urpGradeList.get(i);
            NewGrade newGrade = new NewGrade();
            newGrade.setUrpGrade(grade);
            newGrade.setDetails(urpGradeDetailDao.getUrpGradeDetail(grade.getId()));
            results.put(exam.getCourseId(), newGrade);
        }
        return results;
    }

    private CurrentGrade getCurrentGradeFromSpider(Student student) {
        CurrentGrade currentGrade = newUrpSpiderService.getCurrentTermGrade(student.getAccount().toString(),
                student.getPassword());
        if (CollectionUtils.isEmpty(currentGrade.getList())) {
            return null;
        }
        return currentGrade;
    }

    /**
     * 生成一个UrpGradeAndUrpCourse
     * @param uid 课程号
     * @param term 学期
     * @param urpGrade 成绩实体
     * @param urpGradeDetailList 成绩详情实体
     * @return UrpGradeAndUrpCourse
     */
    private UrpGradeAndUrpCourse generateUrpGradeAndUrpCourse(String uid, Term term, UrpGrade urpGrade, List<UrpGradeDetail> urpGradeDetailList){
        UrpGradeAndUrpCourse target = new UrpGradeAndUrpCourse();
        NewGrade newGrade = new NewGrade();
        newGrade.setDetails(urpGradeDetailList);
        newGrade.setUrpGrade(urpGrade);
        target.setNewGrade(newGrade);
        target.setTerm(term);
        target.setUrpCourse(urpCourseDao.getUrpCourseByUid(uid));
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
            if(!urpCourseDao.ifExistCourse(uid)){
                UrpCourseForSpider urpCourseForSpider = newUrpSpiderService.getCourseFromSpider(student, uid);
                urpCourseDao.insertUrpCourse(urpCourseForSpider.convertToUrpCourse());
            }
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

    public String getElectiveCourseText(List<GradeAndCourse> studentGrades) {
        StringBuffer buffer = new StringBuffer();
        if (studentGrades.size() == 0) {
            buffer.append("尚无选修课成绩");
        } else {
            int allPoint = 0;
            for (GradeAndCourse gradeAndCourse : studentGrades) {
                allPoint += gradeAndCourse.getCourse().getCredit();
                float grade = gradeAndCourse.getGrade().getScore();
                buffer.append("考试名称：").append(gradeAndCourse.getCourse().getName()).append("\n")
                        .append("成绩：").append(grade == -1 ? "" : grade / 100).append("   学分：")
                        .append(((float) gradeAndCourse.getGrade().getPoint()) / 10).append("\n\n");
            }
            allPoint /= 10;
            buffer.insert(0, "- - - - - - - - - - - - - - - \n");
            int num = 0;
            if (allPoint < 7) num = 7 - allPoint;
            buffer.insert(0, "你选修的公共选修课共" + allPoint + "学分\n还差" + num + "学分\n");
            buffer.insert(0, "- - - - - - - - - - - - - - - \n");
        }
        buffer.append("\n 查询仅供参考，以教务网为准，如有疑问微信联系：吴彦祖【hkdhd666】\n（有同学反映，大学英语提高班也是选修课）");
        return buffer.toString();
    }

    private Plan getPlan(UrpGeneralGradeForSpider generalGradeForSpider){
        return planDao.saveOrGetPlanFromDb(generalGradeForSpider.convertToPlan(), generalGradeForSpider.getPlanNumber());
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
