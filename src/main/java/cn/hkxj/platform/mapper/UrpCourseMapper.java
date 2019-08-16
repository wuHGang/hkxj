package cn.hkxj.platform.mapper;


import java.util.List;

import cn.hkxj.platform.pojo.UrpCourse;
import cn.hkxj.platform.pojo.example.UrpCourseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UrpCourseMapper {
    int countByExample(UrpCourseExample example);

    int deleteByExample(UrpCourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UrpCourse record);

    int insertSelective(UrpCourse record);

    List<UrpCourse> selectByExample(UrpCourseExample example);

    UrpCourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UrpCourse record, @Param("example") UrpCourseExample example);

    int updateByExample(@Param("record") UrpCourse record, @Param("example") UrpCourseExample example);

    int updateByPrimaryKeySelective(UrpCourse record);

    int updateByPrimaryKey(UrpCourse record);

    boolean ifExistCourse(@Param("uid") String uid);
}