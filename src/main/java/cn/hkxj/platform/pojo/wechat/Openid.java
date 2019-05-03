package cn.hkxj.platform.pojo.wechat;

import com.google.common.base.MoreObjects;

import java.util.Date;

public class Openid {
    private Integer id;

    private String openid;

    private Integer account;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean isBind;

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

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getIsBind() {
        return isBind;
    }

    public void setIsBind(Boolean isBind) {
        this.isBind = isBind;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("openid", openid)
                .add("account", account)
                .add("gmtCreate", gmtCreate)
                .add("gmtModified", gmtModified)
                .add("isBind", isBind)
                .toString();
    }
}