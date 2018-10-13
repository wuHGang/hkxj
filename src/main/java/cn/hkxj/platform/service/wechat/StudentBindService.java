package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.OpenidExample;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.UrpSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("studentBindService")
public class StudentBindService {
    private static final String template = "account: %s openid: %s is exist";
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private OpenidMapper openidMapper;

    public void studentBind(String openid, String account, String password) throws PasswordUncorrectException, ReadTimeoutException, OpenidExistException {
        if (isStudentBind(openid)){
            throw new OpenidExistException(String.format(template, account, openid));
        }
        if (isStudentExist(account)) {
            saveOpenid(openid, account);
        }
        else {
            Student student = getStudent(account, password);
            studentMapper.insertByStudent(student);
            saveOpenid(openid, account);
        }

    }

    private boolean isStudentBind(String openid) {
        OpenidExample openidExample = new OpenidExample();
        openidExample
                .createCriteria()
                .andOpenidEqualTo(openid);
        List<Openid> openids = openidMapper.selectByExample(openidExample);
        return !(openids.size()==0);
    }

    private boolean isStudentExist(String account) {
        Student student = studentMapper.selectByAccount(Integer.parseInt(account));
        return !(student==null);
    }

    private Student getStudent(String account, String password) throws PasswordUncorrectException, ReadTimeoutException {
        UrpSpider urpSpider = new UrpSpider(account, password);

        return urpSpider.getInformation();
    }

    private int saveStudent(Student student) {
        return studentMapper.insertByStudent(student);
    }

    private int saveOpenid(String openid, String account) {
        Openid save = new Openid();
        save.setOpenid(openid);
        save.setAccount(Integer.parseInt(account));
        return openidMapper.insertSelective(save);
    }
}
