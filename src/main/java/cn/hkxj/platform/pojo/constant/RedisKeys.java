package cn.hkxj.platform.pojo.constant;

public enum RedisKeys {

    EMPTY_ROOM_KEY("app_search_service:emptyRoom:"),

    OPENID_TO_ACCOUNT("mark_openid:"),

    URP_SPIDER_COOKIE("urp_spider_cookie:"),

    URP_COOKIE("urp_cookie"),

    URP_COOKIE_ACCOUNT("urp_cookie_account"),

    URP_LOGIN_COOKIE("urp_login_cookie"),

    KAPTCHA("kaptcha")
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
