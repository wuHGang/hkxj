package cn.hkxj.platform.pojo.constant;

/**
 * @author junrong.chen
 */

public enum RedisKeys {
    /**
     * redis key 实例
     */

    EMPTY_ROOM_KEY("app_search_service:emptyRoom:"),

    OPENID_TO_ACCOUNT("mark_openid:"),

    URP_COOKIE("urp_cookie"),

    URP_LOGIN_COOKIE("urp_login_cookie"),

    CAPTCHA("kaptcha"),
    /**
     * 数据库班级信息id与教务网班级id的映射
     */
    URP_CLASS_CODE("classId_urpClassCode"),
    /**
     * 班级课时排行榜
     */
    CLASS_COURSE_HOUR_RANK("class_courseHour_rank"),
    /**
     * 班级课时排行详情
     */
    CLASS_COURSE_HOUR_DETAIL("class_courseHour_detail"),
    ;
    private String name;

    RedisKeys(String name){
        this.name=name;
    }

    public String genKey(String key){
        return name + "_" + key;
    }

    public String getName(){
        return this.name;
    }

}
