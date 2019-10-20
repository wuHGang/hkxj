package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.CourseDao;
import cn.hkxj.platform.dao.CourseTimeTableDao;
import cn.hkxj.platform.dao.CourseTimeTableDetailDao;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.newmodel.SearchResult;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import cn.hkxj.platform.spider.newmodel.searchclass.ClassInfoSearchResult;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomPost;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomResult;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchResultWrapper;
import cn.hkxj.platform.spider.newmodel.searchclass.CourseTimetableSearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchCoursePost;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchCourseResult;
import cn.hkxj.platform.spider.newmodel.searchteacher.SearchTeacherPost;
import cn.hkxj.platform.spider.newmodel.searchteacher.SearchTeacherResult;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
    @Resource
    private CourseDao courseDao;
    @Resource
    private CourseTimeTableDetailDao courseTimeTableDetailDao;
    @Resource
    private CourseTimeTableDao courseTimeTableDao;

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
        List<SearchResult<ClassInfoSearchResult>> result = newUrpSpiderService.getClassInfoSearchResult(null);
        if(CollectionUtils.isNotEmpty(result)){
            for (SearchResult<ClassInfoSearchResult> searchResult: result) {
                for (ClassInfoSearchResult record : searchResult.getRecords()) {
                    System.out.println(record);
                }

            }

        }
        System.out.println(result.get(0).getRecords().size());
    }

    @Test
    public void testSearchClassTimeTable() {
        for (List<CourseTimetableSearchResult> result : newUrpSpiderService.searchClassTimeTable("2017023081", "1", "2016020002")) {
            System.out.println(result.size());
        }

    }

    @Test
    public void searchTeacherInfo() {
        SearchTeacherPost post = new SearchTeacherPost();
        post.setExecutiveEducationPlanNum("2019-2020-1-1");
        for (SearchResult<SearchTeacherResult> result : newUrpSpiderService.searchTeacherInfo(post)) {
            for (SearchTeacherResult record : result.getRecords()) {
                System.out.println(record);
            }
        }
    }

    @Test
    public void getUrpCourseTimeTableByTeacherAccount() {
        for (List<CourseTimetableSearchResult> result : newUrpSpiderService.searchCourseTimetableByTeacher( "1982800009")) {
            for (CourseTimetableSearchResult searchResult : result) {
                System.out.println(searchResult);
            }

        }

    }

    @Test
    public void searchClassroomInfo() {
        SearchClassroomPost post = new SearchClassroomPost();
        post.setExecutiveEducationPlanNum("2019-2020-1-1");
        for (SearchResultWrapper<SearchClassroomResult> resultWrapper : newUrpSpiderService.searchClassroomInfo(post)) {
            for (SearchClassroomResult result : resultWrapper.getPageData().getRecords()) {
                System.out.println(result);
            }

        }

    }

    @Test
    public void searchCourseInfo() {

        int sum = (2186 / 30) + 1;
        for (int x=1; x<=sum; x++){
            SearchCoursePost post = new SearchCoursePost();
            post.setExecutiveEducationPlanNum("2019-2020-1-1")
            .setPageNum(Integer.toString(x))
            .setPageSize("30");
            for (SearchCourseResult record : newUrpSpiderService.searchCourseInfo(post).getRecords()) {
                String credit = record.getCredit();
                String termName = record.getTermName();
                String termYear = termName.substring(0, 9);
                int termOrder = getTermOrder(termName);

                Course course = new Course()
                        .setName(record.getCourseName())
                        .setNum(record.getCourseId())
                        .setCourseOrder(record.getCourseOrder())
                        .setCredit(credit)
                        .setAcademyCode(record.getAcademyCode())
                        .setAcademyName(record.getAcademyName())
                        .setTeacherName(record.getTeacherNameList())
                        .setTeacherAccount(record.getTermNumber())
                        .setCourseType(record.getCourseTypeName())
                        .setCourseTypeCode(record.getCourseTypeCode())
                        .setExamType(record.getExamTypeName())
                        .setExamTypeCode(record.getExamTypeCode())
                        .setTermYear(termYear)
                        .setTermOrder(termOrder);
            }
        }

    }

    @Test
    public void searchCourseTimeTable(){

        Splitter splitter = Splitter.on(",").omitEmptyStrings().trimResults();
        for (Course course : courseDao.getAllCourse()) {
            List<List<CourseTimetableSearchResult>> searchCourseTimeTable;
            try {
                searchCourseTimeTable = newUrpSpiderService.searchCourseTimeTable(course);
            }catch (Exception e){
                System.out.println(course);
                continue;
            }

            for (List<CourseTimetableSearchResult> resultList : searchCourseTimeTable) {
                for (CourseTimetableSearchResult result : resultList) {
                    if(result.getClassIdList() == null){
                        log.error("重修课{}", result.toString());
                    }

                    for (CourseTimetable timetable : result.transToCourseTimetable()) {
                        List<CourseTimetable> timetableList = courseTimeTableDao.selectByCourseTimetable(timetable);

                        // 这里19级的课数据库中没有对应的记录 应该插入一条新的记录
                        if(timetableList.size() == 0){
                            courseTimeTableDao.insertSelective(timetable);
                        }if(timetableList.size() == 1){
                            log.error("重复数据 {}", timetable);
                        }
                        //这里对应的是某节课存在跨周上课的问题
                        else {
                            for (CourseTimetable tableDetail : timetableList) {
                                System.out.println(tableDetail);
                            }
                        }
                    }
                }

            }
        }



    }

    private int getTermOrder(String termName){
        if(termName.contains("一")){
            return 1;
        }else {
            return 2;
        }

    }



}