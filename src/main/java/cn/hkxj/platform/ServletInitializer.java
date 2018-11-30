package cn.hkxj.platform;

import cn.hkxj.platform.utils.ApplicationUtil;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        ApplicationUtil.setApplicationContext(application.context());
        return application.sources(PlatformApplication.class);
	}

}
