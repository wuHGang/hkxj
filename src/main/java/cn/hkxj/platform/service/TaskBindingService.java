package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.SubscribeGradeUpdateMapper;
import cn.hkxj.platform.mapper.TaskMapper;
import cn.hkxj.platform.pojo.wechat.SubscribeGradeUpdate;
import cn.hkxj.platform.pojo.wechat.Task;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service
public class TaskBindingService {

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    SubscribeGradeUpdateMapper subscribeGradeUpdateMapper;
    //TODO 配置多个公众号时的修改，取消注入改为通过set方法设置
//    @Resource
    private WxMpService wxMpService;

    public WxMpService getWxMpService() {
        return wxMpService;
    }

    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    /***
     *记录哪些用户在后台回复过查询成绩
     * @param openid 用户的openid
     */
    public void subscribeGradeUpdateBinding(String openid){
        if(subscribeGradeUpdateMapper.isOpenidSubscribed(openid)==null){
            Task task=new Task();
            task.setOpenid(openid).setUpdateType(1).setCount(0);
            taskMapper.taskBinding(task);
            SubscribeGradeUpdate subscribeUser=new SubscribeGradeUpdate();
            subscribeUser.setOpenid(openid);
            subscribeGradeUpdateMapper.insert(subscribeUser);
            log.info("new subscribeGradeUpdate binding ");
        }
        try {
            wxMpService.getKefuService().sendKefuMessage(getKefuMessage(openid));
            log.info("send message ok");
        }catch (WxErrorException e) {
            log.error(e.getMessage());
        }

    }

    private WxMpKefuMessage getKefuMessage(String openid) {
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent("我们会推送最新更新的成绩\n" );
        wxMpKefuMessage.setToUser(openid);
        wxMpKefuMessage.setMsgType("text");
        return wxMpKefuMessage;
    }

}
