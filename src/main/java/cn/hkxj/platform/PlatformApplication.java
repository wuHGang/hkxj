package cn.hkxj.platform;


import cn.hkxj.platform.utils.ApplicationUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableRetry
@SpringBootApplication
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
