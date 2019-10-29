package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.CourseDao;
import cn.hkxj.platform.dao.UrpCourseDao;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.SchoolTime;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.UrpCourse;
import cn.hkxj.platform.spider.newmodel.course.UrpCourseForSpider;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchCoursePost;
import cn.hkxj.platform.utils.DateUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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

    public void checkOrSaveUrpCourseToDb(String uid, Student student){
        if (!urpCourseDao.ifExistCourse(uid)) {
            UrpCourseForSpider urpCourseForSpider = newUrpSpiderService.getCourseFromSpider(student, uid);
            urpCourseDao.insertUrpCourse(urpCourseForSpider.convertToUrpCourse());
        }
    }

    public void saveCourse(String courseId, String sequenceNumber){
        if(courseDao.selectCourseByPojo(new Course().setNum(courseId).setCourseOrder(sequenceNumber)).size() == 0){
            SearchCoursePost post = new SearchCoursePost();
            post.setCourseNumber(courseId).setCourseNumber(sequenceNumber);
            SchoolTime time = DateUtils.getCurrentSchoolTime();
            post.setExecutiveEducationPlanNum(time.getTerm().getExecutiveEducationPlanNum());
            newUrpSpiderService.searchCourseInfo(post);
        }
    }

    public UrpCourse getUrpCourseByCourseId(String courseId){

        try {
            return cache.get(courseId, () -> {
                UrpCourse urpCourse = urpCourseDao.getUrpCourseByCourseId(courseId);
                if(urpCourse == null){
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
