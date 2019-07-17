package cn.hkxj.platform.spider.model;

import com.google.common.io.Files;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author junrong.chen
 * 验证码
 */
@Slf4j
public class VerifyCode {
    private final byte[] data;

    public VerifyCode(final byte[] data){
        this.data = data.clone();
    }

    public byte[] getData() {
        return data;
    }

    public void write(String path){
        path = StringUtils.isEmpty(path)? "pic.jpg" : path;
        try {
            Files.write(data, new File(path));
        } catch (IOException e) {
            log.error("write verify code error", e);
        }
    }

    public boolean isEmpty(){
        return data.length == 0;
    }
}
