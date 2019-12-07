package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.experimental.Accessors;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ScheduleTask {
    private Integer id;

    private String openid;

    private Integer taskCount;

    private String appid;

    private Byte sendStatus;

    private Byte isSubscribe;

    private Integer scene;

    private Date gmtCreate;

    private Date gmtModify;

    public ScheduleTask(){}

    public ScheduleTask(WxMpService wxMpService, WxMpXmlMessage wxMpXmlMessage, String scene){
        this.appid = wxMpService.getWxMpConfigStorage().getAppId();
        this.openid = wxMpXmlMessage.getFromUser();
        this.scene = Integer.parseInt(scene);
    }

    public ScheduleTask(String appid, String openid, String scene){
        this.appid = appid;
        this.openid = openid;
        this.scene = Integer.parseInt(scene);
    }
}