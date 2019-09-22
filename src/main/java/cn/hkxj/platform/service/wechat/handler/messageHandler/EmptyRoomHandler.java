package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.constant.LessonOrder;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.timetable.RoomTimeTable;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.EmptyRoomService;
import cn.hkxj.platform.spider.newmodel.emptyroom.EmptyRoomPost;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.StreamSupport;

/**
 * @author junrong.chen
 * @date 2018/10/31
 */
@Component
@Slf4j
public class EmptyRoomHandler implements WxMpMessageHandler {
    private static final String PATTERN = "格式不正确:\n\n具体教室 \n：空教室 教室 主楼E0405\n（主楼教室前要加主楼俩字 科厦教室需要加上科字如：科S308）\n\n查询教学楼的某一层：\n例如查询科厦四楼空教室\n空教室 科厦 4";
    private static Splitter SPLITTER = Splitter.on(" ").trimResults().omitEmptyStrings();
    private static final int CONTENT_SIZE_3 = 3;
    private static final int CONTENT_SIZE_2 = 2;
    private static final String SINGLE_ROOM = "教室";
    @Resource(name = "emptyRoomService")
    private EmptyRoomService emptyRoomService;
    @Resource
    private CourseService courseService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
        String reply = "“”";
        log.info("check empty room success openid:{}", wxMessage.getFromUser());

        return new TextBuilder().build(reply, wxMessage, wxMpService);
    }



}
