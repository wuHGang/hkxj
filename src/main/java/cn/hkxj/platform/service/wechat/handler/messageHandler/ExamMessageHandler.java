package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.mapper.WechatOpenIdMapper;
import cn.hkxj.platform.pojo.Exam;
import cn.hkxj.platform.pojo.Wechatuser;
import cn.hkxj.platform.service.wechat.common.exam.impl.ExamServiceImpl;
import cn.hkxj.platform.service.wechat.handler.AbstractHandler;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author JR Chan
 * @date 2018/6/12 15:16
 */

@Component
public class ExamMessageHandler extends AbstractHandler {

    @Autowired
    private ExamServiceImpl examServiceImpl;

    @Autowired
    private WechatOpenIdMapper openIdMapper;

    @Autowired
    private TextBuilder textBuilder;

    @Autowired
    private TemplateBuilder templateBuilder;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        String openid = wxMpXmlMessage.getFromUser();
        Wechatuser wechatuser = openIdMapper.getStudentByOpenId(openid);
        try {
            List<Exam> examList = examServiceImpl.getListByClassname(wechatuser);
            String examMsg = examServiceImpl.toText(examList);

            return textBuilder.build(examMsg, wxMpXmlMessage, wxMpService);
//            return null;
        } catch (IOException e) {
            this.logger.error("在组装返回信息时出现错误 {}", e.getMessage());
        }
        return null;
    }

}
