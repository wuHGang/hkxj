package cn.hkxj.platform.exceptions;

/**
 * @author Yuki
 * @date 2019/9/9 10:25
 */
public class RoomParseException extends RuntimeException{

    public RoomParseException(String description) {
        super(description);
    }

    public RoomParseException(String description, Exception e) {
        super(description, e);
    }
}
