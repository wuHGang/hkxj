package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.ClassDao;
import cn.hkxj.platform.dao.UrpClassDao;
import cn.hkxj.platform.mapper.ClassesMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.constant.Academy;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.spider.model.UrpStudentInfo;
import cn.hkxj.platform.spider.newmodel.searchclass.ClassInfoSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author JR Chan
 * @date 2018/12/15
 */
@Slf4j
@Service("clazzService")
public class ClassService {
    @Resource
    private ClassDao classDao;
    @Resource
    private SubjectService subjectService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private UrpClassDao urpClassDao;
    @Resource
    private ClassesMapper classesMapper;


    private static Classes parseText(String classname) {
        String[] clazzSplitter = classname.split("-");
        if (clazzSplitter.length < 2) {
            log.error("class name can`t parse {}", classname);
            throw new IllegalArgumentException("can`t parse class name: " + classname);
        }

        Classes classes = new Classes();
        if(clazzSplitter[1].endsWith("班")){
            classes.setNum(Integer.parseInt(clazzSplitter[1].substring(0, clazzSplitter[1].length()-1)));
        }else {
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

    private static int getYearFromAccount(Integer account) {
        String s = account.toString();
        String year = s.substring(2, 4);
        return Integer.parseInt(year);
    }

    /**
     * 将爬回来的学生班级信息解析为对应的班级对象
     * 特殊班级名 财会S2018  采矿卓越班 会计ACA实验班
     *
     * @param studentWrapper 学生信息
     * @return 班级对象
     */
    Classes parseSpiderResult(UrpStudentInfo studentWrapper) {
        Classes classes = new Classes();
        // 所有班级的年级都从学号里面取
        // 班级序号先前置为1 如果是别的序号会被覆盖
        classes.setYear(getYearFromAccount(studentWrapper.getAccount()));
        classes.setNum(1);

        String classname = studentWrapper.getClassname();
        if(Objects.equals("班", classname.substring(classname.length() - 1))){
            classname = classname.substring(0, classname.length() - 1);
        }
        if (classname.startsWith("财会S")) {
           classes.setName(classname);
        } else if (classname.startsWith("采矿卓越")) {
            classes.setName("采矿卓越");
        } else if (classname.startsWith("会计ACA实验")) {
            classes.setName("会计ACA实验");
        } else {
            classes = parseText(classname);
        }

        List<Classes> classesList = classDao.getClassByClassName(classes);

        if (classesList.size() == 1) {
            return classesList.get(0);
        }

        //记录下对应班级超过一个的情况
        if (classesList.size() > 1) {
            log.error("account {} class more than 1 {}", studentWrapper.getAccount(), classesList.toString());
        }
        return buildClazzByStudent(studentWrapper);
    }

    public Classes parseClassFormSearchResult(ClassInfoSearchResult classInfoSearchResult){
        Classes classes = new Classes();
        String classname = classInfoSearchResult.getClassName();
        classes.setNum(1);

        if(classname.endsWith("班")){
            classname = classname.substring(0, classname.length() - 1);
        }
        if (classname.startsWith("财会S")) {
            classes.setName(classname);

        } else if (classname.startsWith("采矿卓越")) {
            classes.setName("采矿卓越");
        } else if (classname.startsWith("会计ACA实验")) {
            classes.setName("会计ACA实验");
        } else {
            classes = parseText(classname);
        }

        classes.setYear(Integer.valueOf(classInfoSearchResult.getAdmissionGrade().substring(2, 4)));
        classes.setAcademy(Academy.getAcademyByUrpCode(Integer.valueOf(classInfoSearchResult.getDepartmentNum())).getAcademyCode());
        Subject subjectByName = subjectService.getSubjectByName(classInfoSearchResult.getSubjectName());
        classes.setSubject(subjectByName.getId());

        return classes;
    }

    private Classes buildClazzByStudent(UrpStudentInfo studentWrapper) {
        Classes classes = parseText(studentWrapper.getClassname());
        classes.setAcademy(Academy.getAcademyCodeByName(studentWrapper.getAcademy()));

        Subject subjectByName = subjectService.getSubjectByName(studentWrapper.getMajor());
        classes.setSubject(subjectByName.getId());

        classDao.insertClass(classes);

        return classes;
    }

    public UrpClass getUrpClassByStudent(Student student){
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();

        UrpClass urpClass;

        Integer classId = student.getClasses();
        Classes classes = classesMapper.selectByPrimaryKey(classId);
        String urpClassCode = hash.get(RedisKeys.URP_CLASS_CODE.getName(), classes.getId().toString());

        if(StringUtils.isEmpty(urpClassCode)){
            String s = classes.getName() + classes.getYear() + "-" + classes.getNum();

            urpClass = urpClassDao.selectByName(s);

            if(urpClass == null){
                log.error("student {}  can`t find urp class", student);
                return null;
            }

            hash.put(RedisKeys.URP_CLASS_CODE.getName(), classes.getId().toString(), urpClass.getClassNum());


        }else {
            urpClass = urpClassDao.selectByClassNumber(urpClassCode);
        }

        if(urpClass == null){
            log.error("student {}  can`t find urp class", student);
            return null;
        }

        return urpClass;
    }


}
