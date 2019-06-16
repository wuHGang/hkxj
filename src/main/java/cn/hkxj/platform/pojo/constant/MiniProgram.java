package cn.hkxj.platform.pojo.constant;

/**
 * @author Yuki
 * @date 2019/6/13 20:47
 * 存储关联小程序的一些信息
 */
public enum MiniProgram {

    APPID("wx05f7264e83fa40e9"),
    COURSE_PATH("pages/core/cj/cj"),
    GRADE_PATH("pages/core/kb/kb");

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
