package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.SubjectMapper;
import cn.hkxj.platform.pojo.Subject;
import cn.hkxj.platform.pojo.SubjectExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
@Service
@Slf4j
public class SubjectService {
    @Resource
    private SubjectMapper subjectMapper;

    public Subject getSubjectByName(String subjectName){
        SubjectExample subjectExample = new SubjectExample();
        subjectExample.createCriteria().andNameEqualTo(subjectName);
        List<Subject> subjectList = subjectMapper.selectByExample(subjectExample);
        if(subjectList.size() > 1){
            log.error("more than one subject {}", subjectList.toString());
        }
        return subjectList.get(0);
    }

    public Subject getSubjectById(int subjectId){
        SubjectExample subjectExample = new SubjectExample();
        subjectExample.createCriteria().andIdEqualTo(subjectId);
        List<Subject> subjectList = subjectMapper.selectByExample(subjectExample);
        if(subjectList.size() > 1){
            log.error("more than one subject {}", subjectList.toString());
        }
        return subjectList.get(0);
    }

}
