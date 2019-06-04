package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

import java.util.Date;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public Byte getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Byte sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Byte getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(Byte isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public Integer getScene() {
        return scene;
    }

    public void setScene(Integer scene) {
        this.scene = scene;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleTask that = (ScheduleTask) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(openid, that.openid) &&
                Objects.equal(taskCount, that.taskCount) &&
                Objects.equal(appid, that.appid) &&
                Objects.equal(sendStatus, that.sendStatus) &&
                Objects.equal(isSubscribe, that.isSubscribe) &&
                Objects.equal(scene, that.scene) &&
                Objects.equal(gmtCreate, that.gmtCreate) &&
                Objects.equal(gmtModify, that.gmtModify);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, openid, taskCount, appid, sendStatus, isSubscribe, scene, gmtCreate, gmtModify);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("openid", openid)
                .add("taskCount", taskCount)
                .add("appid", appid)
                .add("sendStatus", sendStatus)
                .add("isSubscribe", isSubscribe)
                .add("scene", scene)
                .add("gmtCreate", gmtCreate)
                .add("gmtModify", gmtModify)
                .toString();
    }
}