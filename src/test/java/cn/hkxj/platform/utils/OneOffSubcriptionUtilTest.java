package cn.hkxj.platform.utils;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpProProperties;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.wechat.OneOffSubscription;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.annotation.Resource;
import java.util.List;


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
    private TemplateBuilder templateBuilder;
    @Resource
    private WechatTemplateProperties wechatTemplateProperties;

    @Test
    public void getOneOffSubscriptionUrl() {
//        String openid = "lllllllllllllllllllllllllll";
//        WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
//        miniProgram.setAppid(MiniProgram.APPID.getValue());
//        miniProgram.setPagePath(MiniProgram.COURSE_PATH.getValue());
//        List<WxMpTemplateData> templateDataList = templateBuilder.assemblyTemplateContentForCourse("asdasdasd");
//        String url = "http://alshdlahsidhasdhas";
//        WxMpTemplateMessage templateMessage =
//                templateBuilder.build(openid, templateDataList, TemplateBuilder.PLUS_COURSE_TEMPLATE_ID, miniProgram, url);
//        System.out.println(JsonUtils.wxToJson(templateMessage));
        System.out.println("courseTemplateId" + wechatTemplateProperties.getPlusCourseTemplateId());
        System.out.println("gradeUpdateId" + wechatTemplateProperties.getPlusGradeUpdateTemplateId());
        System.out.println("tipsTemplateId" + wechatTemplateProperties.getPlusTipsTemplateId());
    }
}
