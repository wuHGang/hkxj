package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.UrpClassroom;
import cn.hkxj.platform.pojo.example.UrpClassroomExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UrpClassroomMapper {
    int countByExample(UrpClassroomExample example);

    int deleteByExample(UrpClassroomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UrpClassroom record);

    int insertSelective(UrpClassroom record);

    List<UrpClassroom> selectByExample(UrpClassroomExample example);

    UrpClassroom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UrpClassroom record, @Param("example") UrpClassroomExample example);

    int updateByExample(@Param("record") UrpClassroom record, @Param("example") UrpClassroomExample example);

    int updateByPrimaryKeySelective(UrpClassroom record);

    int updateByPrimaryKey(UrpClassroom record);
}