package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.CourseDao;
import cn.hkxj.platform.dao.UrpCourseDao;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.newmodel.SearchResult;
import cn.hkxj.platform.spider.newmodel.course.UrpCourseForSpider;
import cn.hkxj.platform.spider.newmodel.searchclass.CourseTimetableSearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchCoursePost;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchCourseResult;
import cn.hkxj.platform.utils.DateUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/9/2 15:56
 */
@Slf4j
@Service
public class UrpCourseService {

    @Resource
    private UrpCourseDao urpCourseDao;
    @Resource
    private CourseDao courseDao;

    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private UrpSearchService urpSearchService;

    private static final Cache<String, UrpCourse> cache = CacheBuilder.newBuilder()
            .maximumSize(500)
            .build();

    private static final Cache<String, Course> currentTermCourseCache = CacheBuilder.newBuilder()
            .maximumSize(2000)
            .build();

    public void checkOrSaveUrpCourseToDb(String uid, Student student) {
        if (!urpCourseDao.ifExistCourse(uid)) {
            UrpCourseForSpider urpCourseForSpider = newUrpSpiderService.getCourseFromSpider(student, uid);
            urpCourseDao.insertUrpCourse(urpCourseForSpider.convertToUrpCourse());
        }
    }

    public Course getCurrentTermCourse(String courseId, String sequenceNumber) {
        return getCurrentTermCourse(courseId, sequenceNumber, null);

    }

    public Course getCurrentTermCourse(String courseId, String sequenceNumber, Course updateCourse) {
        SchoolTime schoolTime = DateUtils.getCurrentSchoolTime();
        String termYear = schoolTime.getTerm().getTermYear();
        int order = schoolTime.getTerm().getOrder();
        return getCourseFromCache(courseId, sequenceNumber,termYear, order, updateCourse);
    }


    public Course getCourseFromCache(String courseId, String sequenceNumber, String termYear, int termOrder, Course updateCourse){
        String key = courseId + sequenceNumber + termYear + termOrder;
        try {
            return currentTermCourseCache.get(key, () -> {
                Course course = getCourse(courseId, sequenceNumber, termYear,
                        termOrder, updateCourse);

                if (course == null) {
                    log.info(" {} {} {} {}", courseId, sequenceNumber, termYear,
                            termOrder);
                }
                return course;
            });
        } catch (ExecutionException e) {
            log.error("get course cache error key {}", key, e);
            throw new RuntimeException(e);
        }

    }


    /**
     * @param courseId
     * @param sequenceNumber
     * @param termYear
     * @param termOrder
     * @param updateCourse   这个传入的course主要作用是课程查询的时候有比较多缺省的值，从该对象中获取
     * @return
     */
    public Course getCourse(String courseId, String sequenceNumber, String termYear, int termOrder, Course updateCourse) {
        List<Course> courseList = courseDao.selectCourseByPojo(
                new Course()
                        .setNum(courseId)
                        .setCourseOrder(sequenceNumber)
                        .setTermYear(termYear)
                        .setTermOrder(termOrder));

        if (courseList.size() == 0) {
            SearchCoursePost post = new SearchCoursePost();
            post.setCourseNumber(courseId).setCourseOrderNumber(sequenceNumber);
            post.setExecutiveEducationPlanNum(termYear + "-" + termOrder + "-1");
            SearchResult<SearchCourseResult> searchResult = newUrpSpiderService.searchCourseInfo(post);
            if (CollectionUtils.isEmpty(searchResult.getRecords())) {
                courseDao.insertSelective(updateCourse);
                return updateCourse;
            }

            List<SearchCourseResult> resultList = searchResult.getRecords();

            if (resultList.size() > 1) {
                resultList = resultList.stream()
                        .filter(x -> post.getCourseNumber().equals(x.getCourseId()))
                        .collect(Collectors.toList());
                if (resultList.size() != 1) {
                    log.error("search course result more than one. post {} records {}", post, searchResult.getRecords());
                    throw new RuntimeException("search course result more than one");
                }

            } else if (resultList.size() == 0) {
                log.error("search course result empty. post {}", post);
                throw new RuntimeException("search course result more than one");
            }

            Course course = resultList.get(0).transToCourse();
            if (updateCourse != null) {
                course.setCredit(updateCourse.getCredit());
                course.setExamType(updateCourse.getExamType());
                course.setExamTypeCode(updateCourse.getExamTypeCode());
                course.setCourseOrder(updateCourse.getCourseOrder());
                course.setTeacherAccount(course.getTeacherAccount() == null ? "" : course.getTeacherAccount());
            }
            courseDao.insertSelective(course);
            return course;
        }
        return courseList.stream().findFirst().get();
    }


    public void getTimetable(String courseId, String sequenceNumber, String termYear, int termOrder) {
        List<Course> courseList = courseDao.selectCourseByPojo(
                new Course()
                        .setNum(courseId)
                        .setCourseOrder(sequenceNumber)
                        .setTermYear(termYear)
                        .setTermOrder(termOrder));

        if (courseList.size() == 0) {
            SearchCoursePost post = new SearchCoursePost();
            post.setCourseNumber(courseId).setCourseOrderNumber(sequenceNumber);
            post.setExecutiveEducationPlanNum(termYear + "-" + termOrder + "-1");
            SearchResult<SearchCourseResult> searchResult = newUrpSpiderService.searchCourseInfo(post);
            searchResult.getRecords().stream()
                    .flatMap(x -> urpSearchService.searchTimetableByCourse(x.transToCourse()).stream())
                    .map(CourseTimetableSearchResult::transToCourseTimetable)
                    .collect(Collectors.toList());


        }
    }

    /**
     * @param courseId
     * @return
     */
    @Deprecated
    public UrpCourse getUrpCourseByCourseId(String courseId) {

        try {
            return cache.get(courseId, () -> {
                UrpCourse urpCourse = urpCourseDao.getUrpCourseByCourseId(courseId);
                if (urpCourse == null) {
                    UrpCourseForSpider urpCourseForSpider = newUrpSpiderService.getCourseFromSpider("2014025838", "1", courseId);
                    urpCourse = urpCourseForSpider.convertToUrpCourse();
                    urpCourseDao.insertUrpCourse(urpCourse);
                }
                return urpCourse;
            });
        } catch (ExecutionException e) {
            log.error("get course.json cache error", e);
        }

        return urpCourseDao.getUrpCourseByCourseId(courseId);
    }
}
