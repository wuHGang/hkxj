package cn.hkxj.platform.pojo;

import java.util.Date;

public class Task {
    private Integer id;

    private String openid;

    private Integer updateType;

    private Integer count;

    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public Task setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public Integer getUpdateType() {
        return updateType;
    }

    public Task setUpdateType(Integer updateType) {
        this.updateType = updateType;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Task setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }


}
