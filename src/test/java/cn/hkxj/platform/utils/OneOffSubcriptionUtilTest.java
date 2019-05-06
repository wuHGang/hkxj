package cn.hkxj.platform.utils;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatMpProProperties;
import me.chanjar.weixin.mp.api.WxMpService;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author Yuki
 * @date 2018/11/22 11:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
@TestPropertySource(value = "classpath:application-prod.properties")
public class OneOffSubcriptionUtilTest {

    @Resource
    private WechatMpProProperties wechatMpProProperties;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;

    @Test
    public void getOneOffSubscriptionUrl() throws UnsupportedEncodingException, FileNotFoundException {
        WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(wechatMpProProperties.getAppId());
        System.out.println( OneOffSubcriptionUtil.getHyperlinks("点击领取课表", "1005", wxMpService));
    }
}
