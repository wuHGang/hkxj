package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.GradeSearchResult;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.NewUrpSpider;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.concurrent.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class NewUrpSpiderServiceTest {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private NewGradeSearchService newGradeSearchService;

    private ExecutorService cacheThreadPool = Executors.newFixedThreadPool(10);

    @Test
    public void getVerifyCode() {
        Student student = new Student();
        student.setAccount(2016024170);
        student.setPassword("1");
        Classes classes = new Classes();
        classes.setId(316);
        student.setClasses(classes);
        CompletableFuture<GradeSearchResult> completableFuture =
                CompletableFuture.supplyAsync(() ->
                {
                    System.out.println("test");
                    try{
                        GradeSearchResult currentGrade = newGradeSearchService.getCurrentGrade(student);
                        System.out.println(currentGrade.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                    return null; });
        try {
            completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        completableFuture.whenCompleteAsync((gradeSearchResult, throwable) -> {
            System.out.println(gradeSearchResult.getData().toString());
        });
    }

    @Test
    public void getGrade() {
        Student student = new Student();
        student.setAccount(2016024170);
        student.setPassword("1");
        Classes classes = new Classes();
        classes.setId(316);
        student.setClasses(classes);
        Future<GradeSearchResult> submit = cacheThreadPool.submit(
                new Callable<GradeSearchResult>() {
                    @Override
                    public GradeSearchResult call() throws Exception {
                        try {
                            System.out.println("test");
                            newUrpSpiderService.getCurrentTermGrade("2016024170", "1");
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                        return null;
                    }
                });
        while (!submit.isDone()){

        }

    }

    @Test
    public void login() {
        Student student = newUrpSpiderService.getStudentInfo("2014025838", "1");
        log.info(student.toString());

    }



}