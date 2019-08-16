package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.UrpExamMapper;
import cn.hkxj.platform.pojo.Term;
import cn.hkxj.platform.pojo.UrpExam;
import cn.hkxj.platform.pojo.example.UrpExamExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/14 22:13
 */
@Service
public class UrpExamDao {

    @Resource
    private UrpExamMapper urpExamMapper;

    public void insertExam(UrpExam urpExam){
        urpExamMapper.insertSelective(urpExam);
    }

    public UrpExam getUrpExam(String uid, Term term){
        UrpExamExample examExample = new UrpExamExample();
        examExample.createCriteria()
                .andCourseIdEqualTo(uid)
                .andTermNameEqualTo(term.getTermName())
                .andTermCodeEqualTo(term.getTermCode());
        return urpExamMapper.selectByExample(examExample).stream().findFirst().orElse(null);
    }

    public List<UrpExam> getOneClassCurrentTermAllUrpExam(int classId, Term currentTerm){
        UrpExamExample example = new UrpExamExample();
        example.createCriteria()
                .andClassIdEqualTo(classId)
                .andTermCodeEqualTo(currentTerm.getTermCode())
                .andTermNameEqualTo(currentTerm.getTermName());
        return urpExamMapper.selectByExample(example);
    }

    public List<Integer> getOneClassCurrentTermAllUrpExamId(int classId, Term currentTerm){
        return urpExamMapper.getOneClassCurrentTermAllUrpExamId(classId, currentTerm.getTermCode(), currentTerm.getTermName());
    }

    public boolean ifExistExam(String uid, int classId, Term term){
        UrpExamExample example = new UrpExamExample();
        example.createCriteria()
                .andClassIdEqualTo(classId)
                .andCourseIdEqualTo(uid)
                .andTermCodeEqualTo(term.getTermCode())
                .andTermNameEqualTo(term.getTermName());
        return urpExamMapper.countByExample(example) == 1;
    }
}
