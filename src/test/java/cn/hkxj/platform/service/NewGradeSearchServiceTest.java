package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.GradeDetail;
import cn.hkxj.platform.pojo.Student;
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
        long start = System.currentTimeMillis();
        Student student = studentDao.selectStudentByAccount(2018022512);
        newGradeSearchService.getCurrentGrade(student);
        System.out.println(System.currentTimeMillis() - start);
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
        Student student = studentDao.selectStudentByAccount(2019020856);
        List<GradeDetail> gradeDetailList = newGradeSearchService.getCurrentTermGradeFromSpider(student);
        List<Grade> gradeList = gradeDetailList.stream().map(GradeDetail::getGrade).collect(Collectors.toList());
        gradeList.stream().findAny().ifPresent(x-> x.setId(11));
        List<Grade> updateList = newGradeSearchService.checkUpdate(student, gradeList);
        for (Grade update : updateList) {
            System.out.println(update);
        }
        newGradeSearchService.saveUpdateGrade(updateList);

    }

    @Test
    public void getCurrentTermGrade(){
        Student student = studentDao.selectStudentByAccount(2019020856);
        for (Grade grade : newGradeSearchService.getCurrentTermGrade(student)) {
            System.out.println(grade);
        }
        ;





    }
}