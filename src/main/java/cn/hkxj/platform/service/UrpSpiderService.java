package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Academy;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.CourseType;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.StudentWrapper;
import cn.hkxj.platform.spider.UrpCourseSpider;
import cn.hkxj.platform.spider.UrpSpider;
import cn.hkxj.platform.utils.TypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public Student getInformation(int account, String password) throws PasswordUncorrectException {
        UrpSpider urpSpider = new UrpSpider(account, password);
        Map information = urpSpider.getInformation();

        StudentWrapper studentWrapper = new StudentWrapper();
        try {
            BeanUtils.populate(studentWrapper, (Map) information);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage());
            throw new RuntimeException("个人信息json解析出错", e);
        }

        Classes classes = clazzService.parseSpiderResult(studentWrapper);
        Student student = wrapperToStudent(studentWrapper);
        student.setClasses(classes);
        return student;
    }

    public ArrayList<GradeAndCourse> getCurrentGrade(Student student) {
        UrpSpider urpSpider = new UrpSpider(student.getAccount(), student.getPassword());
        Map resultMap;
        try {
            resultMap = urpSpider.getGrade();
        } catch (PasswordUncorrectException e) {
            log.error("account {} urp password error", student.getAccount());
            student.setIsCorrect(false);
            studentMapper.updateByPrimaryKey(student);
            return new ArrayList<>();
        } catch (Exception e) {
            log.error("account {} urp error {}", student.getAccount(), e);
            return new ArrayList<>();
        }
        Map gradeResultMap = (Map) resultMap.get("garde");
        List currentList = (List) gradeResultMap.get("current");

        ArrayList<GradeAndCourse> gradeAndCourses = new ArrayList<>();

        for (Object gradeObject : currentList) {
            GradeAndCourse gradeAndCourse = new GradeAndCourse();
            Map gradeMap = (Map) gradeObject;
            Grade grade = getGrade(gradeMap);
            grade.setAccount(student.getAccount());
            Course course = getCourse(gradeMap, student);
            course.setCredit(grade.getPoint());
            gradeAndCourse.setCourse(course);
            gradeAndCourse.setGrade(grade);
            gradeAndCourses.add(gradeAndCourse);
        }
        return gradeAndCourses;
    }

    public Academy getAcademyByUid(int account, String password, String uid) {
        if (courseMapper.ifExistCourse(uid)) {
            List<Course> courses = courseMapper.selectNameByUid(uid);
            return courses.get(0).getAcademy();
        }
        UrpCourseSpider urpCourseSpider = new UrpCourseSpider(account, password);
        return urpCourseSpider.getAcademyId(uid);
    }

    private Course getCourse(Map gradeMap, Student student) {
        Course course = new Course();
        course.setName((String) gradeMap.get("name"));
        String type = (String) gradeMap.get("type");
        course.setType(CourseType.getCourseByType(type));
        String uid = (String) gradeMap.get("uid");
        course.setUid(uid);
        Academy academy = getAcademyByUid(student.getAccount(), student.getPassword(), uid);
        course.setAcademy(academy);
        return course;
    }

    private Grade getGrade(Map gradeMap) {
        // TODO  爬虫还需要爬去相关的成绩的学期和学年  现在直接写死在程序里面
        Grade grade = new Grade();
        grade.setPoint(TypeUtil.pointToInt(gradeMap.get("credit").toString()));
        String scoreObject = (String) gradeMap.get("grade");
        if (StringUtils.isNotEmpty(scoreObject)) {
            grade.setScore(TypeUtil.gradeToInt(scoreObject));
        } else {
            grade.setScore(-1);
        }
        grade.setTerm((byte) 1);
        grade.setYear(2018);
        grade.setCourseId((String) gradeMap.get("uid"));

        return grade;
    }

    private Student wrapperToStudent(StudentWrapper studentWrapper) {
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

