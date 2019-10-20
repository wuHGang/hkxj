package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.TeacherMapper;
import cn.hkxj.platform.pojo.Teacher;
import cn.hkxj.platform.pojo.TeacherExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherDao {
    @Resource
    private TeacherMapper teacherMapper;


    public List<Teacher> getAllTeacher(){
        TeacherExample example = new TeacherExample();
        return teacherMapper.selectByExample(example);
    }
}
