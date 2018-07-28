package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @author flattery
 * */

@Mapper
@Repository
public interface WechatExamMapper {
    void insertExam(Map<String, Object> params);

    List<Exam> getExamByClassname(String classname);

}
