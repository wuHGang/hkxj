package cn.hkxj.platform.spider;

import com.google.common.base.MoreObjects;

/**
 * @author JR Chan
 * @date 2019/5/2
 */
public class UrpCourse {
    private String uid;
    /**
     * 学时
     */
    private double studyTime;
    /**
     * 本科标识
     */
    private String flag = "";
    /**
     * 学分
     */
    private double credit;
    private String englishName;
    /**
     * 开课院系
     */
    private String academyName;
    /**
     * 开课时间
     */
    private String startTime;
    /**
     * 课程名
     */
    private String name;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(double studyTime) {
        this.studyTime = studyTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("uid", uid)
                .add("studyTime", studyTime)
                .add("flag", flag)
                .add("credit", credit)
                .add("englishName", englishName)
                .add("academyName", academyName)
                .add("startTime", startTime)
                .add("name", name)
                .toString();
    }
}
