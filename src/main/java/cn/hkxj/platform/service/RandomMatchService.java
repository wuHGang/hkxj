package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

@Slf4j
@Service
public class RandomMatchService {
    @Resource
    private OpenIdService openIdService;

    //记录所有已成功匹配到的用户
    private static HashMap<String, String> matchMap = new HashMap<>();

    private LinkedHashSet<String> manWaitingSet = new LinkedHashSet<>();

    private LinkedHashSet<String> womanWaitingSet = new LinkedHashSet<>();

    private ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);

    /**
     * 获取总人数
     * <p>
     * 匹配
     * <p>
     * 消息转发
     * <p>
     * 退出
     */

    /**
     * 返回正在队伍中匹配的人数
     *
     * @return 正在队伍中匹配的人数
     */
    public Integer getTotalWaitingNum() {
        return (manWaitingSet.size() + womanWaitingSet.size());
    }

    /**
     * 返回正在聊天中的人数
     *
     * @return 正在聊天中的人数
     */
    public Integer getTotalMatchingNum() {
        return matchMap.size() / 2;
    }

    /**
     * 返回正在队伍中匹配的男性人数
     *
     * @return 正在队伍中匹配的男性人数
     */
    public Integer getManListNum() {
        return manWaitingSet.size();
    }

    /**
     * 返回正在队伍中匹配的女性人数
     *
     * @return 正在队伍中匹配的女性人数
     */
    public Integer getWomanListNum() {
        return womanWaitingSet.size();
    }

    /**
     * 将用户放入等待队列
     *
     * @return 加入等待队列是否成功
     */
    public boolean addWaitingList(String openid, Student student) {
        //已存在于队列中的用户无法再次进入队列
        if (manWaitingSet.contains(openid) || womanWaitingSet.contains(openid)) {
            return false;
        }

        if (checkSex(student)) {
            manWaitingSet.add(openid);
        } else {
            womanWaitingSet.add(openid);
        }

        scheduledExecutorService.schedule(() -> {
            exitWaitingList(openid, student);
        }, 10, TimeUnit.MINUTES);

        return true;
    }

    /**
     * 用户退出等待队列
     *
     * @return 退出等待队列是否成功
     */
    public boolean exitWaitingList(String openid, Student student) {
        if (!checkState(openid)) {
            return false;
        }
        if (checkSex(student)) {
            return manWaitingSet.remove(student.getAccount());
        } else {
            return womanWaitingSet.remove(student.getAccount());
        }
    }

    public boolean checkSex(Student student) {
        if ("男".equals(student.getSex())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 进行用户匹配，匹配不成功的用户放入等待列表中
     *
     * @param openid openid
     * @param appid appid
     * @return 是否匹配成功
     */
    public boolean match(String openid, String appid) {
        Student student = openIdService.getStudentByOpenId(openid, appid);
        if (checkSex(student)) {
            if (!womanWaitingSet.isEmpty()) {
                for (String s : womanWaitingSet) {
                    matchMap.put(openid, s);
                    matchMap.put(s, openid);
                    womanWaitingSet.remove(s);
                    return true;
                }
            }
        } else {
            if (!manWaitingSet.isEmpty()) {
                for (String s : manWaitingSet) {
                    matchMap.put(openid, s);
                    matchMap.put(s, openid);
                    manWaitingSet.remove(s);
                    return true;
                }
            }
        }
        return addWaitingList(openid, student);
    }

    /**
     * 判断是否已匹配
     * @param openid openid
     * @return 是否已匹配
     */
    public boolean checkState(String openid) {
        return matchMap.containsKey(openid);
    }

    public boolean forwardMessage(WxMpXmlMessage wxMessage, WxMpService wxMpService) {
        String openid = wxMessage.getFromUser();
        String content = wxMessage.getContent();
        String type = wxMessage.getMsgType();
        String forwardOpenid = matchMap.get(openid);
        WxMpKefuMessage wxMpKefuMessage=new WxMpKefuMessage();
        wxMpKefuMessage.setContent(content);
        wxMpKefuMessage.setToUser(forwardOpenid);
        wxMpKefuMessage.setMsgType(type);
        return sendKefuMessage(wxMpService, wxMpKefuMessage);
    }

    /**
     * 提供模板的客服消息发送函数
     *
     * @param wxMpService     wxMpService
     * @param wxMpKefuMessage 客服消息
     * @return 是否发送成功
     */
    private boolean sendKefuMessage(WxMpService wxMpService, WxMpKefuMessage wxMpKefuMessage) {
        try {
            wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
            log.info("send to openid:{} kefuMessage success content:{}", wxMpKefuMessage.getToUser(), wxMpKefuMessage.getContent());
            return true;
        } catch (WxErrorException e) {
            log.error("send to openid:{} kefuMessage fail content:{} message:{}",
                    wxMpKefuMessage.getToUser(), wxMpKefuMessage.getContent(), e);
            return false;
        }
    }
}
