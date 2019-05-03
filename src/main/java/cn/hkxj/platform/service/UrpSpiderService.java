package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.SpiderException;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.Term;
import cn.hkxj.platform.pojo.constant.Academy;
import cn.hkxj.platform.pojo.constant.CourseType;
import cn.hkxj.platform.spider.UrpCourse;
import cn.hkxj.platform.spider.UrpCourseSpider;
import cn.hkxj.platform.spider.UrpSpider;
import cn.hkxj.platform.spider.model.CurrentGrade;
import cn.hkxj.platform.spider.model.EverGrade;
import cn.hkxj.platform.spider.model.Information;
import cn.hkxj.platform.spider.model.TermGrade;
import cn.hkxj.platform.spider.model.UrpGrade;
import cn.hkxj.platform.spider.model.UrpResult;
import cn.hkxj.platform.spider.model.UrpStudentInfo;
import cn.hkxj.platform.utils.TypeUtil;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author JR Chan
 * @date 2018/12/15
 */
@Slf4j
@Service("urpSpiderService")
public class UrpSpiderService {
    @Resource
    private ClazzService clazzService;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private StudentMapper studentMapper;

    private Splitter splitter = Splitter.on("-").trimResults().omitEmptyStrings();

    public Student getInformation(int account, String password) {
        UrpSpider urpSpider = new UrpSpider(account, password);
        UrpResult<Information> information = urpSpider.getInformation();
        if (information.getStatus() == 400) {
            throw new PasswordUncorrectException();
        }

        if (Objects.isNull(information.getData())) {
            throw new SpiderException(information.getMessage());
        }
        UrpStudentInfo urpStudentInfo = information.getData().getUrpStudentInfo();
        Classes classes = clazzService.parseSpiderResult(urpStudentInfo);
        Student student = wrapperToStudent(urpStudentInfo);
        student.setClasses(classes);
        return student;
    }

    //0 获取本学期成绩
    //1 获取往期成绩
    public List<GradeAndCourse> getCurrentGrade(Student student) {
        UrpSpider urpSpider = new UrpSpider(student.getAccount(), student.getPassword());
        UrpResult<CurrentGrade> currentGrade;
        try {
            currentGrade = urpSpider.getCurrentGrade();
            log.error("account {} urp password error", student.getAccount());
            if (currentGrade.getStatus() == 400) {
                student.setIsCorrect(false);
                studentMapper.updateByPrimaryKey(student);
            }

        } catch (Exception e) {
            log.error("account {} urp error {}", student.getAccount(), e);
            return new ArrayList<>();
        }

        if (currentGrade.getStatus() != 200) {
            log.warn("account {} urp error {}", student.getAccount(), currentGrade.getMessage());
            return new ArrayList<>();
        }

        return parseResult(currentGrade.getData(), student);
    }

    List<GradeAndCourse> getEverGrade(Student student) {
        UrpSpider urpSpider = new UrpSpider(student.getAccount(), student.getPassword());
        UrpResult<EverGrade> everGrade = urpSpider.getEverGrade();
        if (everGrade.getStatus() == 400) {
            student.setIsCorrect(false);
            studentMapper.updateByPrimaryKey(student);
        }
        if (everGrade.getStatus() != 200) {
            log.warn("account {} urp error {}", student.getAccount(), everGrade.getMessage());
            return new ArrayList<>();
        }
        return parseResult(everGrade.getData(), student);
    }

    private List<GradeAndCourse> parseResult(CurrentGrade currentGrade, Student student) {
        Term term = new Term(2018, 2019, 2);

        return currentGrade.getUrpGradeList().stream()
                .map(urpGrade -> getGradeAndCourse(urpGrade, student, term))
                .collect(Collectors.toList());
    }

    private List<GradeAndCourse> parseResult(EverGrade everGrade, Student student) {
        List<GradeAndCourse> gradeAndCourses = new ArrayList<>();
        for (TermGrade currentGrade : everGrade.getEverGrade()) {
            Term term = getTerm(currentGrade.getTerm());
            for (UrpGrade urpGrade : currentGrade.getGradeList()) {
                GradeAndCourse gradeAndCourse = getGradeAndCourse(urpGrade, student, term);
                gradeAndCourses.add(gradeAndCourse);
            }
        }
        return gradeAndCourses;
    }

    /**
     * 2018-2019学年第二学期(两学期)
     *
     * @param text
     * @return
     */
    private Term getTerm(String text) {
        Term term = new Term();
        String prefix = text.substring(0, 9);
        List<String> year = splitter.splitToList(prefix);
        term.setStartYear(Integer.parseInt(year.get(0)));
        term.setEndYear(Integer.parseInt(year.get(1)));
        term.setOrder(text.contains("一") ? 1 : 2);
        return term;
    }

    private GradeAndCourse getGradeAndCourse(UrpGrade urpGrade, Student student, Term term) {
        GradeAndCourse gradeAndCourse = new GradeAndCourse();
        Grade grade = getGrade(urpGrade);
        grade.setAccount(student.getAccount());
        gradeAndCourse.setGrade(grade);

        Course course = getCourse(urpGrade, student);
        course.setCredit(grade.getPoint());
        gradeAndCourse.setCourse(course);
        gradeAndCourse.setTerm(term);

        return gradeAndCourse;
    }


    private Academy getAcademyByUid(int account, String password, String uid) {
        if (courseMapper.ifExistCourse(uid)) {
            List<Course> courses = courseMapper.selectNameByUid(uid);
            return courses.get(0).getAcademy();
        }
        UrpCourseSpider urpCourseSpider = new UrpCourseSpider(account, password);
        UrpCourse urpCourse = urpCourseSpider.getUrpCourse(uid);
        return Academy.getAcademyByName(urpCourse.getAcademyName());
    }

    private Course getCourse(UrpGrade urpGrade, Student student) {
        Course course = new Course();
        course.setName(urpGrade.getName());
        String type = urpGrade.getType();
        course.setType(CourseType.getCourseByType(type));
        String uid = urpGrade.getUid();
        course.setUid(uid);
        char c = urpGrade.getUid().charAt(0);
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
            course.setAcademy(Academy.Web);
        } else {
            Academy academy = getAcademyByUid(student.getAccount(), student.getPassword(), uid);
            course.setAcademy(academy);
        }

        return course;
    }

    private Grade getGrade(UrpGrade urpGrade) {
        // TODO  爬虫还需要爬去相关的成绩的学期和学年  现在直接写死在程序里面
        Grade grade = new Grade();
        grade.setPoint((int) (Float.parseFloat(urpGrade.getCredit()) * 10));
        if (StringUtils.isNotEmpty(urpGrade.getGrade())) {
            grade.setScore(TypeUtil.gradeToInt(urpGrade.getGrade()));
        } else {
            grade.setScore(-1);
        }
        grade.setTerm((byte) 2);
        grade.setYear(2018);
        grade.setCourseId((urpGrade.getUid()));

        return grade;
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

