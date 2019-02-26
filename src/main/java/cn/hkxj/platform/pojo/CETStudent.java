package cn.hkxj.platform.pojo;

import java.util.Date;

public class CETStudent {

    private Integer id;

    private String level;

    private Integer account;

    private String name;

    private String classRoom;

    private String examinee;

    private Date gmtCreate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getExaminee() {
        return examinee;
    }

    public void setExaminee(String examinee) {
        this.examinee = examinee;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        return "CETStudent{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", account=" + account +
                ", name='" + name + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", examinee=" + examinee +
                ", gmtCreate=" + gmtCreate +
                '}';
    }
}
