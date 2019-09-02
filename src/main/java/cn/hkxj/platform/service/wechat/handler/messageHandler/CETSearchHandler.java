package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.CETService;
import cn.hkxj.platform.service.OpenIdService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Component
public class CETSearchHandler implements WxMpMessageHandler {
    @Resource
    private TextBuilder textBuilder;
    @Resource
    private OpenIdService openIdService;
    @Resource
    private CETService cetService;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        try {
            Student student = openIdService.getStudentByOpenId(wxMpXmlMessage.getFromUser(), wxMpService.getWxMpConfigStorage().getAppId());
            String examinee = cetService.getCETExaminee(student);
            if(StringUtils.isEmpty(examinee)){
                return textBuilder.build("没有查询到准考证号~" , wxMpXmlMessage, wxMpService);
            }

            String builder = "你的四六级准考证号为:\n" + examinee + "\n\n" +
                    "<a href=\"http://msg.weixiao.qq.com/t/38937075fb5a434af7d49c3fa4f2c8bd \">【成绩查询渠道一】</a>\n" +
                    "\n" +
                    "<a href=\"http://cet.neea.edu.cn/cet \">【成绩查询渠道二（稳定）】</a>\n" +
                    "\n" +
                    "<a href=\"http://www.chsi.com.cn/cet \">【成绩查询渠道三（稳定）】</a>\n" +
                    "\n" +
                    "如果有问题，微信添加黑科校际吴彦祖『hkdhdj666』，让他帮你看看";
            return textBuilder.build(builder, wxMpXmlMessage, wxMpService);
        } catch (Exception e) {
            log.error("在组装返回信息时出现错误", e);
        }

        return textBuilder.build("没有查询到准考证号~" , wxMpXmlMessage, wxMpService);
    }
}
