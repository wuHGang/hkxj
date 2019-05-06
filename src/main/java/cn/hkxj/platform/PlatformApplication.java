package cn.hkxj.platform;


import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.utils.ApplicationUtil;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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
@EnableAdminServer
public class PlatformApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(PlatformApplication.class, args);
        ApplicationUtil.setApplicationContext(applicationContext);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        SpringApplicationBuilder sources = application.sources(PlatformApplication.class);
        ApplicationUtil.setApplicationContext(sources.context());
        return sources;
    }
}
