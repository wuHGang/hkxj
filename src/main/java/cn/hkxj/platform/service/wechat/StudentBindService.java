package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.config.wechat.MiniProgramProperties;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatMpProProperties;
import cn.hkxj.platform.dao.MiniProgramOpenIdDao;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.dao.WechatBindRecordDao;
import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUnCorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.pojo.MiniProgramOpenid;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.WechatBindRecord;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.service.NewUrpSpiderService;
import cn.hkxj.platform.service.ScheduleTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    private WechatMpProProperties wechatMpProProperties;
    @Resource
    private StudentDao studentDao;
    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Value("${domain}")
    private String domain;
    private static final String PATTERN = "<a href=\"%s/bind?openid=%s&appid=%s\">%s</a>";

    private static final String TEXT_LINK = "<a href=\"%s\">%s</a>";
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private MiniProgramProperties miniProgramProperties;
    @Resource
    private MiniProgramOpenIdDao miniProgramOpenIdDao;
    @Resource
    private WechatBindRecordDao wechatBindRecordDao;

    /**
     * 学号与微信公众平台openID关联
     * <p>
     * 现在的一个问题是，如果是从一次订阅的接口路由过来的用户，如何帮他们实现快速绑定呢？
     * 点击地址以后将openID存在session中，查看是否已经绑定
     * <p>
     * 1.数据库中有学生信息，并且密码不正确的，重新通过教务网确认密码
     * 2.数据库中有学生信息且密码正确，直接绑定
     * 3.数据库中没有学生数据，查询后绑定
     *
     * @param openid   微信用户唯一标识
     * @param account  学生教务网账号
     * @param password 学生教务网密码
     * @throws PasswordUnCorrectException 密码不正确异常
     * @throws ReadTimeoutException       读取信息超时异常
     * @throws OpenidExistException       Openid已存在
     */
    public Student studentBind(String openid, String account, String password, String appid) throws OpenidExistException {
        if (miniProgramProperties.getAppId().equals(appid)) {


        } else if (isStudentBind(openid, appid)) {
            throw new OpenidExistException(String.format(template, account, openid));
        }

        Student student = studentLogin(account, password);

        studentBind(student, openid, appid);

        return student;

    }


    /**
     * 用于学生从非微信渠道登录
     *
     * @param account  账号
     * @param password 密码
     * @return 学生信息
     */
    public Student studentLogin(String account, String password) throws PasswordUnCorrectException {
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));

        if (student != null && (!student.getIsCorrect() || !student.getPassword().equals(password))) {

            newUrpSpiderService.checkStudentPassword(account, password);
            studentDao.updatePassword(account, password);

        } else if (student == null) {
            student = newUrpSpiderService.getStudentInfo(account, password);
            studentDao.insertStudent(student);
        }

        return student;
    }

    /**
     * 如果已经存在openid则重新关联绑定，否则插入一条新数据再绑定
     *
     * @param student 学生信息
     * @param openid  微信用户唯一标识
     * @param appid   微信平台对应的id
     */
    private void studentBind(Student student, String openid, String appid) {
        boolean haveOpenId;
        if (Objects.equals(wechatMpPlusProperties.getAppId(), appid)) {
            haveOpenId = openidPlusMapper.isOpenidExist(openid) != null && openidPlusMapper.isOpenidBind(openid) == 0;
        } else if (Objects.equals(wechatMpProProperties.getAppId(), appid)) {
            haveOpenId = openidMapper.isOpenidExist(openid) != null && openidMapper.isOpenidBind(openid) == 0;
        } else if (Objects.equals(miniProgramProperties.getAppId(), appid)) {
            haveOpenId = miniProgramOpenIdDao.isOpenidExist(openid);
        } else {
            throw new RuntimeException("unSupport appid: " + appid);
        }

        if (haveOpenId) {
            updateOpenid(openid, student.getAccount().toString(), appid);
        } else {
            saveOpenid(openid, student.getAccount().toString(), appid);
        }

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

    private void saveOpenid(String openid, String account, String appid) {
        Openid save = new Openid();
        save.setOpenid(openid);
        save.setAccount(Integer.parseInt(account));
        save.setIsBind(true);
        if (Objects.equals(wechatMpPlusProperties.getAppId(), appid)) {
            subscribeGradeUpdateTask(openid, appid);
            openidPlusMapper.insertSelective(save);
        } else if (Objects.equals(wechatMpProProperties.getAppId(), appid)) {
            openidMapper.insertSelective(save);
        } else if (Objects.equals(miniProgramProperties.getAppId(), appid)) {
            miniProgramOpenIdDao.insertSelective(new MiniProgramOpenid().setOpenid(openid).setAccount(Integer.parseInt(account)));
        } else {
            throw new RuntimeException("unSupport appid: " + appid);
        }

    }

    private void updateOpenid(String openid, String account, String appid) {
        Openid update = null;
        String origin = null;
        if (!Objects.equals(miniProgramProperties.getAppId(), appid)) {
            update = getOpenID(openid, appid).get(0);
            update.setAccount(Integer.parseInt(account));
            update.setIsBind(true);
            origin = update.getAccount().toString();
        }


        if (Objects.equals(wechatMpPlusProperties.getAppId(), appid)) {
            openidPlusMapper.updateByPrimaryKey(update);
        } else if (Objects.equals(wechatMpProProperties.getAppId(), appid)) {
            openidMapper.updateByPrimaryKey(update);
        } else if (Objects.equals(miniProgramProperties.getAppId(), appid)) {
            MiniProgramOpenid miniProgramOpenid = miniProgramOpenIdDao.selectByOpenId(openid);
            origin = miniProgramOpenid.getAccount().toString();
            if (!miniProgramOpenid.getAccount().toString().equals(account)) {
                miniProgramOpenIdDao.updateByPrimaryKey(miniProgramOpenid.setAccount(Integer.parseInt(account)).setGmtModified(null));
            } else {
                return;
            }

        } else {
            throw new RuntimeException("unSupport appid: " + appid);
        }

        wechatBindRecordDao.insertSelective(new WechatBindRecord()
                .setOriginAccount(origin)
                .setUpdateAccount(account)
                .setAppid(appid).setOpenid(openid));

    }

    public String getBindUrlByOpenid(String fromUser, String appId, String content) {
        return String.format(PATTERN, domain, fromUser, appId, content);
    }

    public String getTextLink(String url, String content) {
        return String.format(TEXT_LINK, url, content);
    }

    private void subscribeGradeUpdateTask(String openid, String appid) {
        ScheduleTask scheduleTask = new ScheduleTask(appid, openid, SubscribeScene.GRADE_AUTO_UPDATE.getScene());
        scheduleTaskService.checkAndSetSubscribeStatus(scheduleTask, true);
    }

}
