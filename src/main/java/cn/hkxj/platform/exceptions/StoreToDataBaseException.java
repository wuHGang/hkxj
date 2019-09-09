package cn.hkxj.platform.exceptions;

import org.apache.catalina.Store;

/**
 * @author Yuki
 * @date 2019/9/9 10:41
 */
public class StoreToDataBaseException extends RuntimeException{

    public StoreToDataBaseException(RoomParseException e){
        super(e);
    }

    public StoreToDataBaseException(String description) {
        super(description);
    }

    public StoreToDataBaseException(String description, RoomParseException e) {
        super(description, e);
    }
}
