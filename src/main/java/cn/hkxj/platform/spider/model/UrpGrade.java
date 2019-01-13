package cn.hkxj.platform.spider.model;

import com.google.common.base.MoreObjects;

/**
 * @author JR Chan
 * @date 2019/1/12
 */
public class UrpGrade {
    private String credit;
    private String grade;
    private String name;
    private int number;
    private String type;
    private String uid;

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("credit", credit)
                .add("grade", grade)
                .add("name", name)
                .add("number", number)
                .add("type", type)
                .add("uid", uid)
                .toString();
    }
}
