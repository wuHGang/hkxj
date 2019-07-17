package cn.hkxj.platform.exceptions;

/**
 * @author junrong.chen
 * @date 2019/7/17
 */
public class UrpRequestException extends RuntimeException{
    public UrpRequestException(String desc){
        super(desc);
    }
}
