package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.GradeMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.service.NewUrpSpiderService;
import cn.hkxj.platform.spider.newmodel.grade.CurrentGrade;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author junrong.chen
 * @date 2019/6/25
 */
@Service
public class GradeDao {
    @Resource
    private GradeMapper gradeMapper;
    @Resource
    private CourseDao courseDao;
    @Resource
    private NewUrpSpiderService newUrpSpiderService;

    public List<Grade> getCurrentGrade(Student student){
        return gradeMapper.selectByAccount(student.getAccount());
    }

    public List<GradeAndCourse> getGradeFromDB(Student student, Term currentTerm){
        List<Grade> currentGrade = getCurrentGrade(student);
        currentGrade.removeIf(grade -> !grade.getYear().equals(2018) || !grade.getTerm().equals((byte) 2));

        if(currentGrade.isEmpty()){
            return Lists.newArrayListWithExpectedSize(0);
        }

        List<String> courseUidList = currentGrade.stream()
                .map(Grade::getCourseId)
                .collect(Collectors.toList());
        final List<Course> courseList = courseDao.selectCourseByUid(courseUidList);

        return currentGrade.stream()
                .flatMap(grade -> courseList.stream()
                        .filter(course -> grade.getCourseId().equals(course.getUid()))
                        .map(course -> new GradeAndCourse(grade, course, currentTerm)))
                .collect(Collectors.toList());
    }

    public boolean saveCurrentGradeToDb(Student student, CurrentGrade currentGrade){
//        //返回是否有更新的标识位
//        boolean haveUpdate = false;
//        for(UrpGeneralGradeForSpider urpGeneralGradeForSpider : currentGrade.getList()){
//            String uid = urpGeneralGradeForSpider.getId().getCourseNumber();
//            if(!courseDao.ifExistCourse(uid)){
//                Course course = newUrpSpiderService.getCourseFromSpider(student, uid);
//                courseDao.insert(course);
//            }
//            if(gradeMapper.ifExistGrade(student.getAccount(), uid) == 0){
//                haveUpdate = true;
//                Grade grade = urpGeneralGradeForSpider.convertToGrade();
//                gradeMapper.insert(grade);
//            }
//        }
//        return haveUpdate;
        return false;
    }
}
