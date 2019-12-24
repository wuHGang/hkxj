package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.CourseTimeTableDao;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.dao.UrpClassRoomDao;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.dto.CourseTimeTableDetailDto;
import cn.hkxj.platform.pojo.vo.CourseTimeTableVo;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import cn.hkxj.platform.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CourseTimeTableServiceTest {

    @Resource
    private CourseTimeTableService courseTimeTableService;
    @Resource
    private StudentDao studentDao;
    @Resource
    private CourseTimeTableDao courseTimeTableDao;
    @Resource
    private UrpClassRoomDao urpClassRoomDao;

    @Test
    public void getAllCourseTimeTableDetails() {
        Student student = studentDao.selectStudentByAccount(2017025299);
        List<CourseTimeTableDetail> details = courseTimeTableService.getAllCourseTimeTableDetails(student);
        for (CourseTimeTableDetail detail : details) {
            System.out.println(detail);
        }

    }

    @Test
    public void getAllCourseTimeTableDetailDtos() {

        for (CourseTimeTableDetailDto dto : courseTimeTableService.getAllCourseTimeTableDetailDtos(2017025717)) {
            System.out.println(dto);
        }
    }

    @Test
    public void getCourseTimeTableByStudent() {
        Student student = studentDao.selectStudentByAccount(2017025717);
        for (CourseTimeTableVo courseTimeTableVo : courseTimeTableService.getCourseTimeTableByStudent(student)) {
            System.out.println(courseTimeTableVo);
        }

    }


    @Test
    public void fix() {
        for (CourseTimetable courseTimetable : courseTimeTableDao.selectByCourseTimetable(new CourseTimetable())) {
            List<UrpClassroom> classroomList = urpClassRoomDao.selectByClassroom(new UrpClassroom().setName(courseTimetable.getRoomName()));

            if (classroomList.size() == 0) {
                log.error("{} size 0", courseTimetable.getRoomName());
            } else if (classroomList.size() == 1) {
                if (!classroomList.get(0).getNumber().equals(courseTimetable.getRoomNumber())) {
                    log.error("{} number error", courseTimetable);
                }
            } else {
                log.error("{} size more than 1", classroomList);
            }
        }

    }


    @Test
    public void task() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(9);
        List<Student> studentList = studentDao.selectAllStudent();
        CountDownLatch latch = new CountDownLatch(studentList.size());
        for (Student student : studentList) {
            if (student.getIsCorrect()) {
                service.submit(() -> {
                    try {
                        if (student.getIsCorrect()) {
                            long start = System.currentTimeMillis();
                            courseTimeTableService.getAllCourseTimeTableDetails(student);
                            System.out.println(student.getAccount() + "  spend" + (System.currentTimeMillis() - start));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                });
            }

        }
        latch.await();
        System.out.println("finish");
        Thread.sleep(2000L);


    }

    @Test
    public void getCourseTimeTableByStudentFromSpider() {
        Student student = studentDao.selectStudentByAccount(2016024986);
        for (CourseTimeTableVo vo : courseTimeTableService.getCourseTimeTableByStudentFromSpider(student)) {
            System.out.println(vo);
        }

    }

    @Test
    public void testUpdate() {
        for (CourseTimeTableVo vo : courseTimeTableService.getCourseTimeTableByStudent(2017023437)) {
            System.out.println(vo);
        }

        System.out.println("####################");

        for (CourseTimeTableVo vo : courseTimeTableService.updateCourseTimeTableByStudent(2017023437)) {
            System.out.println(vo);
        }


    }


    @Test
    public void fixErrorData() {
        // 按学生分好组，然后再进行抓取
        UrpCourseTimeTableForSpider details = courseTimeTableService.getCourseTimeTableDetails(studentDao.selectStudentByAccount(2017026003));
        List<CourseTimetable> list = courseTimeTableService.getCourseTimetableList(details);
        courseTimeTableService.getCourseTimetableIdList(list);

    }


}