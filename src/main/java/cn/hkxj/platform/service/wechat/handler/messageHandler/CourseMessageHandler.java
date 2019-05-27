package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/7/15 15:39
 */
@Slf4j
@Component
public class CourseMessageHandler implements WxMpMessageHandler {

    private static final String URL = "http://platform.hackerda.com/platform/course/timetable";

    @Resource
    private TemplateBuilder templateBuilder;
    @Resource
    private CourseService courseService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private OpenidMapper openidMapper;
    @Resource
    private OpenidPlusMapper openidPlusMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) {
        if (Objects.equals(wechatMpPlusProperties.getAppId(), wxMpService.getWxMpConfigStorage().getAppId())) {
            OpenidExample openidExample = new OpenidExample();
            openidExample.createCriteria()
                    .andOpenidEqualTo(wxMpXmlMessage.getFromUser());
            Openid openid = openidPlusMapper.selectByExample(openidExample).get(0);
            List<WxMpTemplateData> templateData = assemblyTemplateContent(openid.getAccount());
            String url = "http://platform.hackerda.com/platform/course/timetable";
            WxMpTemplateMessage templateMessage = templateBuilder.buildCourseMessage(wxMpXmlMessage, templateData, url);
            try {
                wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
            return null;
        }
        String examMsg = OneOffSubcriptionUtil.getHyperlinks("点击领取今日课表", "1005", wxMpService);
        return new TextBuilder().build(examMsg, wxMpXmlMessage, wxMpService);
    }

    private String getCurrentDayCourseData() {
        List<CourseTimeTable> courseTimeTables = courseService.getCoursesCurrentDay(2016024170);
        return courseService.toText(courseTimeTables);
    }

    private List<WxMpTemplateData> assemblyTemplateContent(int account) {
        List<CourseTimeTable> courseTimeTableList = courseService.getCoursesCurrentDay(account);
        List<WxMpTemplateData> templateDatas = new ArrayList<>();
        WxMpTemplateData first = new WxMpTemplateData();
        first.setName("first");
        first.setValue("当日课表\n");
        WxMpTemplateData course = new WxMpTemplateData();
        course.setName("keyword1");
        course.setValue("\n" + courseService.toText(courseTimeTableList));
        WxMpTemplateData date = new WxMpTemplateData();
        date.setName("keyword2");
        date.setValue("\n第" + SchoolTimeUtil.getSchoolWeek() + "周   " + SchoolTimeUtil.getDayOfWeekChinese());
        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue("查询仅供参考，以学校下发的课表为准，如有疑问微信添加吴彦祖【hkdhd666】");

        templateDatas.add(first);
        templateDatas.add(course);
        templateDatas.add(date);
        templateDatas.add(remark);

        return templateDatas;
    }

}
