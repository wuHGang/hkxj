package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.timetable.ExamTimeTable;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.ExamTimeTableService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.wechat.handler.AbstractHandler;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author JR Chan
 * @date 2018/6/12 15:16
 */
@Slf4j
@Component
public class ExamMessageHandler extends AbstractHandler {

    @Resource
    private OpenIdService openIdService;
    @Resource
    private ExamTimeTableService examTimeTableService;
    private static final String DATA_NOT_FOUND = "还没有你的考试时间，可以过段时间再来查询";

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        String content;
        try {
            String appid = wxMpService.getWxMpConfigStorage().getAppId();
            Student student = openIdService.getStudentByOpenId(wxMpXmlMessage.getFromUser(), appid);
            List<ExamTimeTable> examTimeTables = examTimeTableService.getExamTimeTableByStudent(student);
            if (CollectionUtils.isEmpty(examTimeTables)) {
                content = DATA_NOT_FOUND;
            } else {
                content = examListToText(examTimeTables);
            }
        } catch (Exception e){
            log.error("account:{}  get exam message error", e);
            content = DATA_NOT_FOUND;
        }

        return new TextBuilder().build(content, wxMpXmlMessage, wxMpService);
    }

    private String examListToText(List<ExamTimeTable> examTimeTables) {
        examTimeTables.sort((o1, o2) -> (o1.getStart().compareTo(o2.getStart())));
        StringBuffer stringBuffer = new StringBuffer();
        for (ExamTimeTable examTimeTable : examTimeTables) {
            DateTime start = new DateTime(examTimeTable.getStart());
            DateTime end = new DateTime(examTimeTable.getEnd());

            stringBuffer.append(examTimeTable.getCourse().getName()).append('\n');
            stringBuffer.append(examTimeTable.getRoom().getName()).append('\n');
            stringBuffer.append(start.getYear()).append("年").append(start.getMonthOfYear()).append("月");
            stringBuffer.append(start.getDayOfMonth()).append("号").append('\n');
            stringBuffer.append("第").append(examTimeTable.getSchoolWeek()).append("周 ");
            stringBuffer.append(SchoolTimeUtil.getDayOfWeekChinese(examTimeTable.getWeek())).append("\n");
            stringBuffer.append(dateTimeToText(start)).append(" - ").append(dateTimeToText(end)).append('\n');
            stringBuffer.append(Minutes.minutesBetween(start, end).getMinutes()).append("：分钟").append("\n\n");
        }
        return new String(stringBuffer);
    }

    private String dateTimeToText(DateTime dateTime) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(dateTime.getHourOfDay()).append(":").append(dateTime.getMinuteOfHour());
        return new String(stringBuffer);
    }

}
