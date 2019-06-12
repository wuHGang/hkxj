package cn.hkxj.platform.pojo.constant;

public enum RedisKeys {

    EMPTY_ROOM_KEY("app_search_service:emptyRoom:");

    private String name;

    RedisKeys(String name){
        this.name=name;
    }

    public static RedisKeys getRedisKeyByName(String name){
        switch (name){
            case "search_service:emptyRoom:":
                return EMPTY_ROOM_KEY;
            default:
                throw new IllegalArgumentException("no redisKeys match:" + name);
        }
    }

    public String getName(){
        return this.name;
    }

}
