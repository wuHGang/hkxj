package cn.hkxj.platform.pojo;

import cn.hkxj.platform.pojo.constant.Academy;
import cn.hkxj.platform.pojo.constant.CourseType;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;

public class Course {
    private Integer id;

    private String uid;

    private String name;

    private Academy academy;

    private Integer credit;

    private CourseType type;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
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

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("uid", uid)
                .add("name", name)
                .add("academy", academy)
                .add("credit", credit)
                .add("type", type)
                .add("gmtCreate", gmtCreate)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course that = (Course) o;

        return Objects.equal(this.uid, that.uid) &&
                Objects.equal(this.name, that.name) &&
                Objects.equal(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uid, name, credit);
    }
}