package cn.hkxj.platform.exceptions;

import java.io.IOException;

/**
 * @author JR Chan
 * @date 2018/12/12
 */
public class SpiderException extends RuntimeException {
    public SpiderException(String desc) {
        super(desc);
    }


    public SpiderException(String description, IOException e) {
        super(description, e);
    }
}
