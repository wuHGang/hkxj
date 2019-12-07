package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.CourseDao;
import cn.hkxj.platform.dao.UrpCourseDao;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.newmodel.SearchResult;
import cn.hkxj.platform.spider.newmodel.course.UrpCourseForSpider;
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

    private static final Cache<String, UrpCourse> cache = CacheBuilder.newBuilder()
            .maximumSize(500)
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

    public Course getCurrentTermCourse(String courseId, String sequenceNumber, Grade grade) {
        SchoolTime schoolTime = DateUtils.getCurrentSchoolTime();
        return getCourse(courseId, sequenceNumber, schoolTime.getTerm().getTermYear(),
                schoolTime.getTerm().getOrder(), grade);

    }

    public Course getCourse(String courseId, String sequenceNumber, String termYear, int termOrder) {
        return getCourse(courseId, sequenceNumber, termYear, termOrder, null);
    }

    /**
     * @param courseId
     * @param sequenceNumber
     * @param termYear
     * @param termOrder
     * @param grade          这个传入的grade主要作用是课程查询的时候有比较多缺省的值，从该对象中获取
     * @return
     */
    public Course getCourse(String courseId, String sequenceNumber, String termYear, int termOrder, Grade grade) {
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
                searchResult = newUrpSpiderService.searchCourseBasicInfo(post);
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
            if (grade != null) {
                course.setCredit(grade.getCredit().toString());
                course.setExamType(grade.getExamTypeName());
                course.setExamTypeCode(grade.getExamTypeCode());
                course.setCourseOrder(grade.getCourseOrder());
            }
            courseDao.insertSelective(course);
            return course;
        }
        return courseList.stream().findFirst().get();
    }

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
