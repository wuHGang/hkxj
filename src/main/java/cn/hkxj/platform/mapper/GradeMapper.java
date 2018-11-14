package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

import java.util.List;


/**
 * @author junrong.chen
 */
@Mapper
@Repository
public interface GradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);

    List<Grade> selectByAccount(int account);

    int ifExistGrade(@Param(value = "account") int account , @Param(value = "courseId") String courseId);
}