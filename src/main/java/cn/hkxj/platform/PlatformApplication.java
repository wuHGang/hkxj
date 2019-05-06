package cn.hkxj.platform;


import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.utils.ApplicationUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

@PropertySource(value = "classpath:application-local.properties", ignoreResourceNotFound = true)
@EnableScheduling
@SpringBootApplication
public class PlatformApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(PlatformApplication.class, args);
        ApplicationUtil.setApplicationContext(applicationContext);
    }
}
