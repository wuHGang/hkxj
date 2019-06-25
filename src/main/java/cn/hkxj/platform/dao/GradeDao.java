package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.GradeMapper;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2019/6/25
 */
@Service
public class GradeDao {
    @Resource
    private GradeMapper gradeMapper;

    public List<Grade> getCurrentGrade(Student student){
        return gradeMapper.selectByAccount(student.getAccount());
    }
}
