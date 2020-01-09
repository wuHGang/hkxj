package cn.hkxj.platform.service;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.MiniProgramOpenIdDao;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.exceptions.UrpEvaluationException;
import cn.hkxj.platform.exceptions.UrpException;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.vo.GradeVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/8/1 20:15
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class NewGradeSearchServiceTest {

    @Resource
    private NewGradeSearchService newGradeSearchService;
    @Resource
    private StudentDao studentDao;
    @Resource
    private MiniProgramOpenIdDao miniProgramOpenIdDao;


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
    public void getCurrentTermGradeSync(){
        Student student = studentDao.selectStudentByAccount(2016023195);
        for (GradeVo grade : newGradeSearchService.getCurrentTermGradeSync(student)) {
            System.out.println(grade);
        }
    }

    @Test
    public void getCurrentTermGrade(){
        Student student = studentDao.selectStudentByAccount(2019030404);
        for (GradeVo grade : newGradeSearchService.getCurrentTermGrade(student)) {
            System.out.println(grade);
        }
    }

    @Test
    public void getSchemeGrade(){
        Student student = studentDao.selectStudentByAccount(2017025838);
        newGradeSearchService.getSchemeGrade(student);

    }



    @Test
    public void testProxy(){
        Student student = studentDao.selectStudentByAccount(2019030404);
        for (int x=0; x<10 ; x++){
            try {
                newGradeSearchService.getCurrentTermGradeSync(student);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                System.out.println(x);
            }

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void updateAllGrade(){
        List<Student> studentList = studentDao.selectAllStudent();
        MDCThreadPool pool = new MDCThreadPool(8, 8,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "gradeSearch"));

        CountDownLatch latch = new CountDownLatch(studentList.size());

        for (Student student : studentList) {

            pool.submit(() ->{
                long l = System.currentTimeMillis();
                try {
                    log.info("{} start", student.getAccount());
                    if(student.getIsCorrect()){
                        newGradeSearchService.getCurrentTermGradeSync(student);
                    }

                }catch (Exception e){
                    if(e instanceof UrpException){
                        log.error("error {}", e.getMessage());
                    }else if(e instanceof UrpEvaluationException){
                        log.error("error {}", e.getMessage());
                    }else {
                        log.error("error", e);
                    }

                }finally {
                    latch.countDown();
                    log.info("{} finish in {}ms", student.getAccount(), (System.currentTimeMillis() - l));
                }

            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}