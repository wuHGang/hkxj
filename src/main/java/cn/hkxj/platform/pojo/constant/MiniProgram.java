package cn.hkxj.platform.pojo.constant;

/**
 * @author Yuki
 * @date 2019/6/13 20:47
 * 存储关联小程序的一些信息
 */
public enum MiniProgram {

    /**
     * 小程序的appid
     */
    APPID("wx05f7264e83fa40e9"),
    /**
     * 小程序课表的网页url
     */
    COURSE_PATH("pages/core/cj/cj"),
    /**
     * 小程序成绩网页的url
     */
    GRADE_PATH("pages/core/kb/kb"),
    /**
     * 小程序首页
     */
    INDEX("pages/index/index");

    private String value;

    MiniProgram(String value){
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
