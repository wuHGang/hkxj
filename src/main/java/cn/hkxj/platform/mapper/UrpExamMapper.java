package cn.hkxj.platform.mapper;


import java.util.List;

import cn.hkxj.platform.pojo.UrpExam;
import cn.hkxj.platform.pojo.example.UrpExamExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UrpExamMapper {
    int countByExample(UrpExamExample example);

    int deleteByExample(UrpExamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UrpExam record);

    int insertSelective(UrpExam record);

    List<UrpExam> selectByExample(UrpExamExample example);

    UrpExam selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UrpExam record, @Param("example") UrpExamExample example);

    int updateByExample(@Param("record") UrpExam record, @Param("example") UrpExamExample example);

    int updateByPrimaryKeySelective(UrpExam record);

    int updateByPrimaryKey(UrpExam record);

    List<Integer> getOneClassCurrentTermAllUrpExamId(@Param("classId") int classId,
                                                     @Param("termCode") String termCode, @Param("termName") String termName);
}