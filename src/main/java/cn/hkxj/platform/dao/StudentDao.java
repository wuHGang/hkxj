package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentDao {
    @Resource
    private StudentMapper studentMapper;

    public Student selectStudentByAccount(int account){
        return studentMapper.selectByAccount(account);
    }

    public void insertStudent(Student student){
        studentMapper.insert(student);
    }
}
