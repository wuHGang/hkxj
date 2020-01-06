package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.MiniProgramProperties;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.dao.MiniProgramOpenIdDao;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.MiniProgramOpenid;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author junrong.chen
 * @date 2018/10/22
 */
@Service("openIdService")
public class OpenIdService {
    @Resource
    private OpenidMapper openidMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private OpenidPlusMapper openidPlusMapper;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private MiniProgramProperties miniProgramProperties;
    @Resource
    private MiniProgramOpenIdDao miniProgramOpenIdDao;
    @Resource
    private StudentDao studentDao;

    public boolean openidIsExist(String openid, String appid) {
        return getOpenid(openid, appid).size() == 1;
    }

    public boolean openidIsBind(String openid, String appid) {
        if (isPlus(appid)) {
            return openidPlusMapper.isOpenidBind(openid) == 1;
        }
        return openidMapper.isOpenidBind(openid) == 1;
    }

    public List<Openid> getOpenid(String openid, String appid) {
        OpenidExample openidExample = new OpenidExample();
        openidExample
                .createCriteria()
                .andOpenidEqualTo(openid);
        if (isPlus(appid)) {
            return openidPlusMapper.selectByExample(openidExample);
        }
        return openidMapper.selectByExample(openidExample);
    }

    public Student getStudentByOpenId(String openid, String appid) {
        if (miniProgramProperties.getAppId().equals(appid)) {
            List<MiniProgramOpenid> openidList = miniProgramOpenIdDao.selectByPojo(new MiniProgramOpenid().setOpenid(openid));
            if (openidList.size() == 1) {
                return studentDao.selectStudentByAccount(openidList.get(0).getAccount());
            }
            return null;
        }

        List<Openid> openidList = getOpenid(openid, appid);
        if (openidList.size() == 0) {
            throw new IllegalArgumentException("user not bind openid: " + openid + " appid: " + appid);
        }
        Integer account = openidList.get(0).getAccount();
        return studentMapper.selectByAccount(account);
    }

    public void openIdUnbind(String openid, String appid) {
        if (isPlus(appid)) {
            openidPlusMapper.openidUnbind(openid);
        } else {
            openidMapper.openidUnbind(openid);
        }
    }

    /**
     * 对于密码错误的账号全平台解绑
     * @param account
     */
    public void openIdUnbindAllPlatform(int account) {
        OpenidExample openidExample = new OpenidExample();
        openidExample.createCriteria().andAccountEqualTo(account);
        openidPlusMapper.updateByExampleSelective(new Openid().setIsBind(false), openidExample);
        openidMapper.updateByExampleSelective(new Openid().setIsBind(false), openidExample);
    }

    public List<String> getAllOpenidsFromOneClass(int classId, String openid, String appid) {
        if (isPlus(appid)) {
            return openidPlusMapper.getAllOpenidsFromOneClass(classId, openid);
        } else {
            return openidMapper.getAllOpenidsFromOneClass(classId, openid);
        }
    }

    private boolean isPlus(String appid) {
        return Objects.equals(wechatMpPlusProperties.getAppId(), appid);
    }
}
