package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.CourseTimeTableBasicInfoMapper;
import cn.hkxj.platform.pojo.CourseTimeTableBasicInfo;
import cn.hkxj.platform.pojo.example.CourseTimeTableBasicInfoExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Yuki
 * @date 2019/8/30 10:08
 */
@Service
public class CourseTimeTableBasicInfoDao {

    @Resource
    private CourseTimeTableBasicInfoMapper courseTimeTableBasicInfoMapper;

    public void insertCourseTimeTableBasicInfo(CourseTimeTableBasicInfo basicInfo){
        courseTimeTableBasicInfoMapper.insertSelective(basicInfo);
    }

    public CourseTimeTableBasicInfo getCourseTimeTableBasicInfoById(Integer id){
        return courseTimeTableBasicInfoMapper.selectByPrimaryKey(id);
    }

    public CourseTimeTableBasicInfo getCourseTimeTableBasicInfo(CourseTimeTableBasicInfo courseTimeTableBasicInfo){
        CourseTimeTableBasicInfoExample example = new CourseTimeTableBasicInfoExample();
        example.createCriteria()
                .andCourseIdEqualTo(courseTimeTableBasicInfo.getCourseId())
                .andTermYearEqualTo(courseTimeTableBasicInfo.getTermYear())
                .andTermOrderEqualTo(courseTimeTableBasicInfo.getTermOrder())
                .andPlanIdEqualTo(courseTimeTableBasicInfo.getPlanId());
        return courseTimeTableBasicInfoMapper.selectByExample(example).stream().findFirst().orElse(null);
    }

    public void updateCourseTimeTableBasicInfo(CourseTimeTableBasicInfo basicInfo){
        courseTimeTableBasicInfoMapper.updateByPrimaryKeySelective(basicInfo);
    }

    public boolean ifExistCourseTimeTableBasicInfo(CourseTimeTableBasicInfo courseTimeTableBasicInfo){
        CourseTimeTableBasicInfoExample example = new CourseTimeTableBasicInfoExample();
        example.createCriteria()
                .andCourseIdEqualTo(courseTimeTableBasicInfo.getCourseId())
                .andTermYearEqualTo(courseTimeTableBasicInfo.getTermYear())
                .andTermOrderEqualTo(courseTimeTableBasicInfo.getTermOrder())
                .andPlanIdEqualTo(courseTimeTableBasicInfo.getPlanId());
        return courseTimeTableBasicInfoMapper.countByExample(example) == 1;
    }

    /**
     * 将从爬虫爬取到的数据判断是需要存入数据库还是从数据库中进行获取
     * @param convertFromSpider 从爬虫爬取的信息中转化的考试实体
     * @return 考试实体
     */
    public CourseTimeTableBasicInfo saveOrGetCourseTimeTableBasicInfoFromDb(CourseTimeTableBasicInfo convertFromSpider){
        if(!ifExistCourseTimeTableBasicInfo(convertFromSpider)){
            insertCourseTimeTableBasicInfo(convertFromSpider);
        } else {
            convertFromSpider = getCourseTimeTableBasicInfo(convertFromSpider);
        }
        return convertFromSpider;
    }
}
