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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/10/13
 */
@Service("studentBindService")
@Slf4j
public class StudentBindService {
    private static final String template = "account: %s openid: %s is exist";
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private OpenidMapper openidMapper;

	/**
	 * 学号与微信公众平台openID关联
	 *
	 * 现在的一个问题是，如果是从一次订阅的接口路由过来的用户，如何帮他们实现快速绑定呢？
	 * 点击地址以后将openID存在session中，查看是否已经绑定
	 *
	 * @param openid 微信用户唯一标识
	 * @param account 学生教务网账号
	 * @param password 学生教务网密码
	 * @throws PasswordUncorrectException
	 * @throws ReadTimeoutException
	 * @throws OpenidExistException
	 */
    public Student studentBind(String openid, String account, String password) throws PasswordUncorrectException, ReadTimeoutException, OpenidExistException {
        if (isStudentBind(openid)){
            throw new OpenidExistException(String.format(template, account, openid));
        }
	    Student student = null;
        if (isStudentExist(account)) {
            saveOpenid(openid, account);
        }
        else {
            student = getStudentBySpider(account, password);
			studentBind(student, openid);
        }
		return student;
    }

	/**
	 * 用于学生从非微信渠道登录
	 * @param account 账号
	 * @param password 密码
	 * @return 学生信息
	 */
	public Student studentLogin(String account, String password) throws PasswordUncorrectException {
		Student student = getStudentByDB(Integer.parseInt(account));
		if (student == null){
			student = getStudentBySpider(account, password);
			saveStudent(student);
		}
		return student;
	}

	public Student studentBind(Student student, String openid){
		studentMapper.insertByStudent(student);
		saveOpenid(openid, student.getAccount().toString());
		return student;
	}

    public boolean isStudentBind(String openid) {
        return getOpenID(openid).size() != 0;
    }

    private boolean isStudentExist(String account) {
        Student student = studentMapper.selectByAccount(Integer.parseInt(account));
        return student!=null;
    }

    private Student getStudentBySpider(String account, String password) throws PasswordUncorrectException, ReadTimeoutException {
		log.info("urpSpider start");
        UrpSpider urpSpider = new UrpSpider(account, password);

        return urpSpider.getInformation();
    }

    private Student getStudentByDB(int account) {
	    return studentMapper.selectByAccount(account);
    }

    public Student getStudentByOpenID(String openid) {
	    List<Openid> openidList = getOpenID(openid);
	    if (openidList.size() != 0){
	    	return getStudentByDB(openidList.get(0).getAccount());
	    }
	    throw new RuntimeException("用户未绑定");

    }

    private List<Openid> getOpenID(String openid) {
	    OpenidExample openidExample = new OpenidExample();
	    openidExample
			    .createCriteria()
			    .andOpenidEqualTo(openid);
	    return openidMapper.selectByExample(openidExample);
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
