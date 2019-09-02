package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.UrpCourseDao;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.UrpCourse;
import cn.hkxj.platform.spider.newmodel.UrpCourseForSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    private NewUrpSpiderService newUrpSpiderService;

    public void checkOrSaveUrpCourseToDb(String uid, Student student){
        if (!urpCourseDao.ifExistCourse(uid)) {
            UrpCourseForSpider urpCourseForSpider = newUrpSpiderService.getCourseFromSpider(student, uid);
            urpCourseDao.insertUrpCourse(urpCourseForSpider.convertToUrpCourse());
        }
    }

    public UrpCourse getUrpCourseByCourseId(String courseId){
        return urpCourseDao.getUrpCourseByCourseId(courseId);
    }
}
