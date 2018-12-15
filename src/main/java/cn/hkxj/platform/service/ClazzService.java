package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.ClassesMapper;
import cn.hkxj.platform.pojo.Academy;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ClassesExample;
import cn.hkxj.platform.pojo.StudentWrapper;
import cn.hkxj.platform.pojo.Subject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JR Chan
 * @date 2018/12/15
 */
@Slf4j
@Service("clazzService")
public class ClazzService {
    @Resource
    private ClassesMapper classesMapper;
    @Resource
    private SubjectService subjectService;

    private static Classes parseText(String classname) {
        String[] clazzSplitter = classname.split("-");
        if (clazzSplitter.length < 2) {
            return null;
        }

        Classes classes = new Classes();
        if (clazzSplitter[1].length() > 1) {
            String num = clazzSplitter[1].substring(0, 1);
            classes.setNum(Integer.parseInt(num));
        } else {
            classes.setNum(Integer.parseInt(clazzSplitter[1]));
        }
        int length = clazzSplitter[0].length();
        for (int i = 0; i < length; i++) {
            char c = clazzSplitter[0].charAt(i);
            if (c >= '0' && c <= '9') {
                String year = clazzSplitter[0].substring(i, length);
                classes.setYear(Integer.parseInt(year));

                classes.setName(clazzSplitter[0].substring(0, i));
                //此时的targets[0]是专业名,targets[1]是班级在所在的序号
                return classes;
            }
        }
        return classes;
    }

    public static void main(String[] args) {
        ClazzService clazzService = new ClazzService();
        Classes classes = clazzService.parseText("汉语言18-2班");
        System.out.println(classes.toString());
    }

    Classes parseSpiderResult(StudentWrapper studentWrapper) {

        List<Classes> classesList = selectFromDB(studentWrapper);

        if (classesList.size() == 1) {
            return classesList.get(0);
        }

        //记录下对应班级超过一个的情况
        if (classesList.size() > 1) {
            log.error("account {} class more than 1 {}", studentWrapper.getAccount(), classesList.toString());
        }
        return buildClazzByStudent(studentWrapper);
    }

    private List<Classes> selectFromDB(StudentWrapper studentWrapper) {
        Classes classes = parseText(studentWrapper.getClassname());
        ClassesExample classesExample = new ClassesExample();
        classesExample.createCriteria()
                .andNameEqualTo(classes.getName())
                .andNumEqualTo(classes.getNum())
                .andYearEqualTo(classes.getYear());
        return classesMapper.selectByExample(classesExample);
    }

    private Classes buildClazzByStudent(StudentWrapper studentWrapper) {
        Classes classes = parseText(studentWrapper.getClassname());
        classes.setAcademy(Academy.getAcademyCodeByName(studentWrapper.getAcademy()));

        Subject subjectByName = subjectService.getSubjectByName(studentWrapper.getMajor());
        classes.setSubject(subjectByName.getId());

        classesMapper.insert(classes);

        return classes;
    }
}
