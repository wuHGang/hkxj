package cn.hkxj.platform.pojo;

import cn.hkxj.platform.pojo.constant.Academy;
import com.google.common.base.MoreObjects;

public class Subject {
    private Integer id;

    private String name;

    private Academy academy;

    private String code;

    private Integer approveTime;

    private String propertyFirst;

    private String propertySecond;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Integer approveTime) {
        this.approveTime = approveTime;
    }

    public String getPropertyFirst() {
        return propertyFirst;
    }

    public void setPropertyFirst(String propertyFirst) {
        this.propertyFirst = propertyFirst == null ? null : propertyFirst.trim();
    }

    public String getPropertySecond() {
        return propertySecond;
    }

    public void setPropertySecond(String propertySecond) {
        this.propertySecond = propertySecond == null ? null : propertySecond.trim();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("academy", academy)
                .add("code", code)
                .add("approveTime", approveTime)
                .add("propertyFirst", propertyFirst)
                .add("propertySecond", propertySecond)
                .toString();
    }
}