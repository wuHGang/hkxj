package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.exceptions.PasswordUnCorrectException;
import cn.hkxj.platform.exceptions.UrpException;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.UrpClassroom;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.model.UrpStudentInfo;
import cn.hkxj.platform.spider.newmodel.SearchResult;
import cn.hkxj.platform.spider.newmodel.course.UrpCourseForSpider;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import cn.hkxj.platform.spider.newmodel.evaluation.EvaluationPagePost;
import cn.hkxj.platform.spider.newmodel.evaluation.EvaluationPost;
import cn.hkxj.platform.spider.newmodel.evaluation.searchresult.TeachingEvaluation;
import cn.hkxj.platform.spider.newmodel.examtime.UrpExamTime;
import cn.hkxj.platform.spider.newmodel.grade.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.grade.detail.GradeDetailSearchPost;
import cn.hkxj.platform.spider.newmodel.grade.detail.UrpGradeDetailForSpider;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGeneralGradeForSpider;
import cn.hkxj.platform.spider.newmodel.grade.scheme.Scheme;
import cn.hkxj.platform.spider.newmodel.searchclass.ClassInfoSearchResult;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomPost;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomResult;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchResultWrapper;
import cn.hkxj.platform.spider.newmodel.searchclass.CourseTimetableSearchResult;
import cn.hkxj.platform.spider.newmodel.searchclass.SearchClassInfoPost;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchCoursePost;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchCourseResult;
import cn.hkxj.platform.spider.newmodel.searchteacher.SearchTeacherPost;
import cn.hkxj.platform.spider.newmodel.searchteacher.SearchTeacherResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 第一次登录成功后，将学号对应session的cookie持久化
 * 如果需要不使用验证码登录，使用之前需要校验该账号是否有可用的cookie
 *
 *
 *
 * @author junrong.chen
 * @date 2019/7/18
 */
@Slf4j
@Service
public class NewUrpSpiderService {
    @Resource
    private ClassService classService;
    @Resource
    private StudentDao studentDao;
    @Resource
    private OpenIdService openIdService;

    @Retryable(value = UrpException.class, maxAttempts = 3)
    CurrentGrade getCurrentTermGrade(Student student){
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getCurrentGrade();
    }

    CurrentGrade getCurrentTermGrade(String account){
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));
        return getCurrentTermGrade(student);
    }

    /**
     * 这个方法只有基本得成绩信息  包括相信成绩信息的抓取使用{@see #getCurrentTermGrade()}
     */
    @Retryable(value = UrpException.class, maxAttempts = 3)
    List<UrpGeneralGradeForSpider> getCurrentGeneralGrade(Student student){
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getCurrentGeneralGrade();
    }

    List<UrpGeneralGradeForSpider> getCurrentGeneralGrade(String account){
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));
        return getCurrentGeneralGrade(student);
    }

    UrpCourseForSpider getCourseFromSpider(String account, String password, String uid){
        NewUrpSpider spider = getSpider(account, password);
        return spider.getUrpCourse(uid);
    }



    UrpCourseForSpider getCourseFromSpider(Student student, String uid){
        return getCourseFromSpider(student.getAccount().toString(), student.getPassword(), uid);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<SearchResult<ClassInfoSearchResult>> getClassInfoSearchResult(SearchClassInfoPost searchClassInfoPost){
        Student student = studentDao.selectStudentByAccount(2014025838);
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getClassInfoSearchResult(searchClassInfoPost);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<List<CourseTimetableSearchResult>> searchClassTimeTable(String account, String password, String classCode){
        NewUrpSpider spider = getSpider(account, password);
        return spider.getUrpCourseTimeTableByClassCode(classCode);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<SearchResult<SearchTeacherResult>> searchTeacherInfo(SearchTeacherPost searchTeacherPost){
        Student student = studentDao.selectStudentByAccount(2014025838);
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.searchTeacherInfo(searchTeacherPost);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<List<CourseTimetableSearchResult>> searchCourseTimetableByTeacher(String teacherNumber){
        Student student = studentDao.selectStudentByAccount(2014025838);
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getUrpCourseTimeTableByTeacherAccount(teacherNumber);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<List<CourseTimetableSearchResult>> getCourseTimeTableByClassroom(UrpClassroom urpClassroom){
        Student student = studentDao.selectStudentByAccount(2014025838);
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getUrpCourseTimeTableByClassroomNum(urpClassroom);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<SearchResultWrapper<SearchClassroomResult>> searchClassroomInfo(SearchClassroomPost searchClassroomPost){
        Student student = studentDao.selectStudentByAccount(2014025838);
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.searchClassroomInfo(searchClassroomPost);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public SearchResult<SearchCourseResult> searchCourseInfo(SearchCoursePost searchCoursePost){
        Student student = studentDao.selectStudentByAccount(2014025838);
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.searchCourseInfo(searchCoursePost);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public SearchResult<SearchCourseResult> searchCourseBasicInfo(SearchCoursePost searchCoursePost){
        Student student = studentDao.selectStudentByAccount(2014025838);
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.searchCourseBasicInfo(searchCoursePost).getData();
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<List<CourseTimetableSearchResult>> searchCourseTimeTable(Course course){
        Student student = studentDao.selectStudentByAccount(2014025838);
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getUrpCourseTimeTableByCourse(course);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public void checkStudentPassword(String account, String password){
        new NewUrpSpider(account, password);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public UrpCourseTimeTableForSpider getUrpCourseTimeTable(Student student){
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getUrpCourseTimeTable();
    }


    @Retryable(value = UrpException.class, maxAttempts = 3)
    public TeachingEvaluation searchTeachingEvaluationInfo(Student student){
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.searchTeachingEvaluationInfo();
    }


    @Retryable(value = UrpException.class, maxAttempts = 3)
    public void evaluate(Student student, EvaluationPost evaluationPost){
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        spider.evaluation(evaluationPost);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public String getEvaluationToken(Student student, EvaluationPagePost evaluationPagePost){
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getEvaluationToken(evaluationPagePost);
    }

    /**
     * 获取学生信息
     */
    @Retryable(value = UrpException.class, maxAttempts = 3)
    public Student getStudentInfo(String account, String password){
        NewUrpSpider spider = getSpider(account, password);

        return getUserInfo(spider.getStudentInfo());
    }


    /**
     * 考试安排
     * @return
     */
    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<UrpExamTime> getExamTime(String account, String password){
        NewUrpSpider spider = getSpider(account, password);

        return spider.getExamTime();
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<UrpExamTime> getExamTime(Student student){
        return getExamTime(student.getAccount().toString(), student.getPassword());
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public UrpGradeDetailForSpider getGradeDetail(Student student, GradeDetailSearchPost gradeDetailSearchPost){
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getUrpGradeDetail(gradeDetailSearchPost);
    }


    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<Scheme> getSchemeGrade(Student student){
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getSchemeGrade();
    }


    private Student getUserInfo(UrpStudentInfo studentInfo){

        Classes classes = classService.parseSpiderResult(studentInfo);
        Student student = wrapperToStudent(studentInfo);
        student.setClasses(classes.getId());

        return student;
    }

    private NewUrpSpider getSpider(String account, String password){
        try {
            return new NewUrpSpider(account, password);
        }catch (PasswordUnCorrectException e){
            studentDao.updatePasswordUnCorrect(Integer.parseInt(account));
            openIdService.openIdUnbindAllPlatform(Integer.parseInt(account));
            throw e;
        }

    }

    private Student wrapperToStudent(UrpStudentInfo studentWrapper) {
        Student student = new Student();
        student.setAccount(studentWrapper.getAccount());
        student.setPassword(studentWrapper.getPassword());
        student.setEthnic(studentWrapper.getEthnic());
        student.setSex(studentWrapper.getSex());
        student.setName(studentWrapper.getName());
        student.setIsCorrect(true);
        return student;
    }
}
