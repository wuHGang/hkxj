package cn.hkxj.platform.exceptions;

public class OpenidExistException extends RuntimeException{
    public OpenidExistException(String description) {
        super(description);
    }
}
