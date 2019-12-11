package cn.hkxj.platform.config.wechat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat.miniprogram")
public class MiniProgramProperties {

    private String appId;

    private String secret;
}
