package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.CourseTimeTableDetail;
import cn.hkxj.platform.pojo.SchoolTime;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.dto.CourseTimeTableDetailDto;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import cn.hkxj.platform.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yuki
 * @date 2019/9/2 16:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class CourseTimeTableServiceTest {

    @Resource
    private CourseTimeTableService courseTimeTableService;
    @Resource
    private StudentDao studentDao;

    @Test
    public void getAllCourseTimeTableDetails(){
        Student student = studentDao.selectStudentByAccount(2017025299);
        List<CourseTimeTableDetail> details = courseTimeTableService.getAllCourseTimeTableDetails(student);
        for (CourseTimeTableDetail detail : details) {
            System.out.println(detail);
        }

    }

    @Test
    public void getAllCourseTimeTableDetailDtos() {

        for (CourseTimeTableDetailDto dto : courseTimeTableService.getAllCourseTimeTableDetailDtos(2017025299)) {
            System.out.println(dto);
        }
        ;


    }

    @Test
    public void task() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(9);
        List<Student> studentList = studentDao.selectAllStudent();
        CountDownLatch latch = new CountDownLatch(studentList.size());
        for (Student student : studentList) {
            if(student.getIsCorrect()){
                service.submit(() ->{
                    try {
                        if(student.getIsCorrect()){
                            long start = System.currentTimeMillis();
                            courseTimeTableService.getAllCourseTimeTableDetails(student);
                            System.out.println(student.getAccount() + "  spend"+ (System.currentTimeMillis() - start));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        latch.countDown();
                    }
                });
            }

        }
        latch.await();
        System.out.println("finish");
        Thread.sleep(2000L);


    }

}