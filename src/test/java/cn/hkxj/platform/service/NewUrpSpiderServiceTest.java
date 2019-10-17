package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.GradeSearchResult;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.newmodel.SearchPost;
import cn.hkxj.platform.spider.newmodel.SearchResult;
import cn.hkxj.platform.spider.newmodel.course.UrpCourseForSpider;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomPost;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomResult;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchResultWrapper;
import cn.hkxj.platform.spider.newmodel.searchcourse.ClassCourseSearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.ClassInfoSearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.Records;
import cn.hkxj.platform.spider.newmodel.searchteacher.SearchTeacherPost;
import cn.hkxj.platform.spider.newmodel.searchteacher.SearchTeacherResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;
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
    @Resource
    private StudentDao studentDao;

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
                    try {
                        GradeSearchResult currentGrade = newGradeSearchService.getCurrentGrade(student);
                        System.out.println(currentGrade.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    return null;
                });
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
                () -> {
                    try {
                        System.out.println("test");
                        newUrpSpiderService.getCurrentTermGrade("2016024170", "1");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    return null;
                });
        while (!submit.isDone()) {

        }

    }

    @Test
    public void login() {
        Student student = newUrpSpiderService.getStudentInfo("2014025838", "1");
        log.info(student.toString());

    }

    @Test
    public void test() {
        NewUrpSpider spider = new NewUrpSpider("2016020685", "1");
        spider.getExamTime();
    }

    @Test
    public void testCourseTimeTable(){
        Student student = studentDao.selectStudentByAccount(2017025278);
        UrpCourseTimeTableForSpider timeTable = newUrpSpiderService.getUrpCourseTimeTable(student);


    }

    @Test
    public void testMakeUpGrade() {
//        NewUrpSpider spider = new NewUrpSpider("2017023081", "1");
        MakeUpService makeUpService=new MakeUpService();
        System.out.println(makeUpService.getMakeUpService("2017023081", "1"));
    }

    @Test
    public void testGetClassInfoSearchResult() {
        List<ClassInfoSearchResult> result = newUrpSpiderService.getClassInfoSearchResult("2017023081", "1", null);
        if(CollectionUtils.isNotEmpty(result)){
            for (ClassInfoSearchResult searchResult : result) {
                for (Records record : searchResult.getRecords()) {
                    System.out.println(record);
                }

            }

        }
        System.out.println(result.get(0).getRecords().size());
    }

    @Test
    public void testSearchClassTimeTable() {
        for (List<ClassCourseSearchResult> result : newUrpSpiderService.searchClassTimeTable("2017023081", "1", "2016020002")) {
            System.out.println(result.size());
        }

    }

    @Test
    public void searchTeacherInfo() {
        SearchTeacherPost post = new SearchTeacherPost();
        post.setExecutiveEducationPlanNum("2019-2020-1-1");
        for (SearchResult<SearchTeacherResult> result : newUrpSpiderService.searchTeacherInfo("2014025838", "1",
                post)) {
            for (SearchTeacherResult record : result.getRecords()) {
                System.out.println(record);
            }
        }
    }

    @Test
    public void getUrpCourseTimeTableByTeacherAccount() {
        for (List<ClassCourseSearchResult> result : newUrpSpiderService.getUrpCourseTimeTableByTeacherAccount("2017023081", "1", "1982800009")) {
            for (ClassCourseSearchResult searchResult : result) {
                System.out.println(searchResult);
            }

        }

    }

    @Test
    public void searchClassroomInfo() {
        SearchClassroomPost post = new SearchClassroomPost();
        post.setExecutiveEducationPlanNum("2019-2020-1-1");
        for (SearchResultWrapper<SearchClassroomResult> resultWrapper : newUrpSpiderService.searchClassroomInfo("2017023081", "1", post)) {
            for (SearchClassroomResult result : resultWrapper.getPageData().getRecords()) {
                System.out.println(result);
            }

        }



    }




}