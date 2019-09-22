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

    CAPTCHA("kaptcha")
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
