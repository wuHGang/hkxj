package cn.hkxj.platform.exceptions;

import java.io.IOException;

/**
 * @author junrong.chen
 * @date 2019/7/17
 */
public class UrpTimeoutException extends RuntimeException {
    public UrpTimeoutException(String description, IOException e) {
        super(description, e);
    }
}
