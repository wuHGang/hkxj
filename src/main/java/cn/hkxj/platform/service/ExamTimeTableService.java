package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.mapper.ClassesMapper;
import cn.hkxj.platform.mapper.ExamTimeTableMapper;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ExamTimeTable;
import cn.hkxj.platform.pojo.ExamTimeTableExample;
import cn.hkxj.platform.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
@Slf4j
@Service
public class ExamTimeTableService {
    @Resource
    private AppSpiderService appSpiderService;
    @Resource
    private ClassService classService;
    @Resource
    private ExamTimeTableMapper examTimeTableMapper;


    public List<ExamTimeTable> getExamTimeTableByStudent(Student student) {
        Classes clazz = classService.getClassByStudent(student);
        List<Integer> timeTableIdList = examTimeTableMapper.selectExamIdIdByClassId(clazz.getId());
        if(timeTableIdList.size() == 0){
            ArrayList<ExamTimeTable> examList = null;
            try {
                examList = appSpiderService.getExamByAccount(student.getAccount());
            } catch (PasswordUncorrectException e) {
                return new ArrayList<ExamTimeTable>();
            }
            if(CollectionUtils.isEmpty(examList)){
                return examList;
            }

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            ArrayList<ExamTimeTable> finalExamList = examList;
            executorService.execute(() -> saveExamTimeTask(clazz.getId(), finalExamList));
            return examList;
        }
        else {
            ExamTimeTableExample examTimeTableExample = new ExamTimeTableExample();
            examTimeTableExample.createCriteria()
                    .andIdIn(timeTableIdList);
            return examTimeTableMapper.selectByExample(examTimeTableExample);
        }
    }

    /**
     * 保存好考试时间数据之后，再保存班级和
     * @param classId
     * @param examTimeTableList
     */
    private void saveExamTimeTask(int classId, List<ExamTimeTable> examTimeTableList){
        log.info("task run");
        ArrayList<Integer> examIdList = new ArrayList<>();
        for (ExamTimeTable examTimeTable : examTimeTableList) {
            examIdList.add(examTimeTableMapper.insert(examTimeTable));
        }

        for (Integer examId : examIdList) {
            examTimeTableMapper.insertClassAndExamRelation(classId, examId);
        }


    }
}
