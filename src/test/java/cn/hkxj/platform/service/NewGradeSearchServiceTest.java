package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.GradeDetail;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.UrpGradeAndUrpCourse;
import cn.hkxj.platform.pojo.vo.GradeVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/8/1 20:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class NewGradeSearchServiceTest {

    @Resource
    private NewGradeSearchService newGradeSearchService;
    @Resource
    private StudentDao studentDao;

    @Test
    public void test(){
        Student student = studentDao.selectStudentByAccount(2018022512);
        for (UrpGradeAndUrpCourse course : newGradeSearchService.getCurrentGrade(student).getData()) {
            System.out.println(course);
        }

    }


    @Test
    public void getCurrentTermGradeFromSpider(){
        Student student = studentDao.selectStudentByAccount(2019020856);
        List<GradeDetail> gradeDetailList = newGradeSearchService.getCurrentTermGradeFromSpider(student);
        List<Grade> gradeList = gradeDetailList.stream().map(GradeDetail::getGrade).collect(Collectors.toList());

        newGradeSearchService.saveUpdateGrade(gradeList);


    }

    @Test
    public void checkUpdate(){
        Student student = studentDao.selectStudentByAccount(2017021593);
        List<GradeDetail> gradeDetailList = newGradeSearchService.getCurrentTermGradeFromSpider(student);
        List<Grade> gradeList = gradeDetailList.stream().map(GradeDetail::getGrade).collect(Collectors.toList());

        List<Grade> updateList = newGradeSearchService.checkUpdate(student, gradeList);
        for (Grade update : updateList) {
            System.out.println(update);
        }
//        newGradeSearchService.saveUpdateGrade(updateList);

    }

    @Test
    public void getCurrentTermGrade(){
        Student student = studentDao.selectStudentByAccount(2018024221);
        for (GradeVo grade : newGradeSearchService.getCurrentTermGradeSync(student)) {
            System.out.println(grade);
        }
        ;





    }
}