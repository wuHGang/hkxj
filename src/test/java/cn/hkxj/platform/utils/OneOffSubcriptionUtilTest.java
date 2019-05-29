package cn.hkxj.platform.utils;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpProProperties;
import cn.hkxj.platform.pojo.wechat.OneOffSubscription;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.annotation.Resource;


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

    @Test
    public void getOneOffSubscriptionUrl() {
        WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(wechatMpProProperties.getAppId());
        System.out.println(OneOffSubcriptionUtil.getOneOffSubscriptionUrl("1005", wxMpService));
        OneOffSubscription oneOffSubscription = new OneOffSubscription.Builder()
                .touser("asdasda")
                .scene("1005")
                .title("今日课表asdasdasddddsadasdasdasdasdasd")
                .templateId("templateId")
                .data("今天没有课啊")
                .build();
        System.out.println(JsonUtils.wxToJson(oneOffSubscription));
    }
}
