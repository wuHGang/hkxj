package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.ClassesMapper;
import cn.hkxj.platform.pojo.Academy;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ClassesExample;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.Subject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
@Slf4j
@Service
public class ClassService {
    @Resource
    private ClassesMapper classMapper;
    @Resource
    private SubjectService subjectService;

    public Classes getClassByStudent(Student student){
        String[] classNameSplit = student.getClasses().getClassname().split("-");
        int num = getClassNum(classNameSplit[1]);
        int year = getClassYear(classNameSplit[0]);

        ClassesExample classExample = new ClassesExample();
        //.andSubjectEqualTo(getSubjectByName(student.getMajor()).getId());
        classExample.createCriteria()
                .andAcademyEqualTo(student.getClasses().getAcademy())
                .andYearEqualTo(year)
                .andNumEqualTo(num)
                .andSubjectEqualTo(getSubjectByName(student.getClasses().getName()).getId());
        List<Classes> classes = classMapper.selectByExample(classExample);
        if (classes.size() != 1){
            log.error("class size error: "+classes.toString());
            throw new IllegalArgumentException("class size error: "+classes.toString());
        }
        return classes.get(0);
    }

    private static int getClassNum(String classNum){
        if(classNum.length() > 1){
            return Integer.parseInt(classNum.substring(0, 1));
        } else {
            return Integer.parseInt(classNum);
        }
    }

    private static int getClassYear(String classYear) {
        for(int i = 0; i < classYear.length(); i++){
            char c = classYear.charAt(i);
            if(c >= '0' && c <= '9'){
                //year代表第几级 如16级
                return Integer.parseInt(classYear.substring(i, classYear.length()));
            }
        }
        throw new IllegalArgumentException(classYear);
    }

    private List<Classes> getStudentClassesList(String[] strs, String academy){
        ClassesExample example = new ClassesExample();
        example.createCriteria()
                .andNameEqualTo(strs[0])
                .andYearEqualTo(Integer.parseInt(strs[1]))
                .andNumEqualTo(Integer.parseInt(strs[2]))
                .andAcademyEqualTo(Academy.getAcademyCodeByName(academy));
        return classMapper.selectByExample(example);
    }

    private Subject getSubjectByName(String subjectName) {
        return subjectService.getSubjectByName(subjectName);
    }


}
