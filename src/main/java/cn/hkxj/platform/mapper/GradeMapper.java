package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author junrong.chen
 */
@Mapper
public interface GradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);
}