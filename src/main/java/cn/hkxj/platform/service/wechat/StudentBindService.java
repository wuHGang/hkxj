package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.NewUrpSpiderService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author junrong.chen
 * @date 2018/10/13
 */
@Service("studentBindService")
@Slf4j
public class StudentBindService {
    private static final String template = "account: %s openid: %s is exist";

    @Resource
    private OpenidMapper openidMapper;
    @Resource
    private OpenidPlusMapper openidPlusMapper;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private StudentDao studentDao;
    @Resource
    private CourseService courseService;
    @Resource
    private NewUrpSpiderService newUrpSpiderService;

    /**
     * 学号与微信公众平台openID关联
     * <p>
     * 现在的一个问题是，如果是从一次订阅的接口路由过来的用户，如何帮他们实现快速绑定呢？
     * 点击地址以后将openID存在session中，查看是否已经绑定
     *
     * @param openid   微信用户唯一标识
     * @param account  学生教务网账号
     * @param password 学生教务网密码
     * @throws PasswordUncorrectException 密码不正确异常
     * @throws ReadTimeoutException       读取信息超时异常
     * @throws OpenidExistException       Openid已存在
     */
    public Student studentBind(String openid, String account, String password, String appid) throws PasswordUncorrectException, ReadTimeoutException, OpenidExistException {
        if (isStudentBind(openid, appid)) {
            throw new OpenidExistException(String.format(template, account, openid));
        }
        //openid在数据库中分为两种状态时可以重新绑定
        //1:数据库存在openid,is_bind=0
        //2:数据库不存在openid
        boolean result;
        if (Objects.equals(wechatMpPlusProperties.getAppId(), appid)) {
            result = openidPlusMapper.isOpenidExist(openid) != null && openidPlusMapper.isOpenidBind(openid) == 0;
        } else {
            result = openidMapper.isOpenidExist(openid) != null && openidMapper.isOpenidBind(openid) == 0;
        }
        newUrpSpiderService.checkStudentPassword(account, password);
        if (result) {
            //可以重新绑定
            Student student = null;
            if (isStudentExist(account)) {
                updateOpenid(openid, account, appid);
                studentDao.updatePassword(account, password);
            } else {
                student = newUrpSpiderService.getStudentInfo(account, password);
                studentDao.insertStudent(student);
                updateOpenid(openid, account, appid);
            }
            return student;
        } else {
            Student student = null;
            if (isStudentExist(account)) {
                saveOpenid(openid, account, appid);
            } else {
                student = newUrpSpiderService.getStudentInfo(account, password);
                studentBind(student, openid, appid);
            }
            return student;
        }

    }


    /**
     * 用于学生从非微信渠道登录
     *
     * @param account  账号
     * @param password 密码
     * @return 学生信息
     */
    public Student studentLogin(String account, String password) throws PasswordUncorrectException {
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));
        if (student == null) {
            student = newUrpSpiderService.getStudentInfo(account, password);
            studentDao.insertStudent(student);
        }
        return student;
    }


    public Student studentBind(Student student, String openid, String appid) {
        studentDao.insertStudent(student);
        saveOpenid(openid, student.getAccount().toString(), appid);
        return student;
    }

    public boolean isStudentBind(String openid, String appid) {
        List<Openid> openids = getOpenID(openid, appid);
        if (openids.size() == 0)
            return false;
        else {
            Openid openidEntity = openids.get(0);
            return openidEntity.getIsBind();
        }
    }

    private boolean isStudentExist(String account) {
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));
        return student != null;
    }

    public Student getStudentByOpenID(String openid, String appid) {
        List<Openid> openidList = getOpenID(openid, appid);
        if (openidList.size() != 0) {
            return studentDao.selectStudentByAccount(openidList.get(0).getAccount());
        }
        throw new RuntimeException("用户未绑定");

    }

    private List<Openid> getOpenID(String openid, String appid) {
        OpenidExample openidExample = new OpenidExample();
        openidExample
                .createCriteria()
                .andOpenidEqualTo(openid);
        if (Objects.equals(wechatMpPlusProperties.getAppId(), appid)) {
            return openidPlusMapper.selectByExample(openidExample);
        }
        return openidMapper.selectByExample(openidExample);
    }

    private int saveOpenid(String openid, String account, String appid) {
        Openid save = new Openid();
        save.setOpenid(openid);
        save.setAccount(Integer.parseInt(account));
        save.setIsBind(true);
        if (Objects.equals(wechatMpPlusProperties.getAppId(), appid)) {
            return openidPlusMapper.insertSelective(save);
        }
        return openidMapper.insertSelective(save);
    }

    private int updateOpenid(String openid, String account, String appid) {
        Openid update = getOpenID(openid, appid).get(0);
        update.setAccount(Integer.parseInt(account));
        update.setIsBind(true);
        if (Objects.equals(wechatMpPlusProperties.getAppId(), appid)) {
            return openidPlusMapper.updateByPrimaryKey(update);
        }
        return openidMapper.updateByPrimaryKey(update);
    }


    private void sendMessage(String scene, String appid, String openid, String account){
        if(!Objects.isNull(appid)){
            if(Objects.equals(wechatMpPlusProperties.getAppId(), appid)){
                WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(appid);
                if(Objects.equals("1005", scene)){
                    List<CourseTimeTable> courseTimeTableList = courseService.getCoursesCurrentDay(Integer.parseInt(account));
                    WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
                    wxMpKefuMessage.setMsgType("text");
                    wxMpKefuMessage.setContent(courseService.toText(courseTimeTableList));
                    wxMpKefuMessage.setToUser(openid);
                    try {
                        wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
                        log.info("send kefuMessage about course success openid:{} appid:{}", openid, appid);
                    } catch (WxErrorException e) {
                        log.info("send kefuMessage about course failed openid:{} appid:{}", openid, appid);
                    }
                }
            }
        }
    }
}
