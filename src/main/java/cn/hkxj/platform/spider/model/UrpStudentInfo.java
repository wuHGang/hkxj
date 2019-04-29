package cn.hkxj.platform.spider.model;

import com.google.common.base.MoreObjects;

/**
 * @author JR Chan
 * @date 2018/12/15
 */
public class UrpStudentInfo {
    public String academy;
    private Integer account;
    private String password;
    private String name;
    private String sex;
    private String ethnic;
    private String major;
    private String classname;


    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("academy", academy)
                .add("account", account)
                .add("password", password)
                .add("name", name)
                .add("sex", sex)
                .add("ethnic", ethnic)
                .add("major", major)
                .add("classname", classname)
                .toString();
    }
}
