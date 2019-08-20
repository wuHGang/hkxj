package cn.hkxj.platform.exceptions;

/**
 * @author junrong.chen
 * @date 2019/7/18
 */
public class UrpVerifyCodeException extends RuntimeException{
    public UrpVerifyCodeException(String desc){
        super(desc);
    }
}
