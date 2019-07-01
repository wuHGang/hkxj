package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.timetable.ExamTimeTable;
import cn.hkxj.platform.service.ExamTimeTableService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
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
public class ExamMessageHandler implements WxMpMessageHandler {

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
                content = newExamListToText(examTimeTables, student);
            }
        } catch (Exception e){
            log.error("account:{}  get exam message error", e);
            content = DATA_NOT_FOUND;
        }

        return new TextBuilder().build(content, wxMpXmlMessage, wxMpService);
    }

    private String newExamListToText(List<ExamTimeTable> examTimeTables,Student student) {
        examTimeTables.sort((o1, o2) -> (o1.getStart().compareTo(o2.getStart())));
        StringBuffer stringBuffer = new StringBuffer();
        DateTime dateTime = new DateTime(examTimeTables.get(0).getStart());
        stringBuffer.append(student.getName()).append("同学，你的").append(dateTimeYear(dateTime)).append("学年第").append(dateTimeTerm(dateTime)).append("学期的考试时间如下：\n\n");
        for (ExamTimeTable examTimeTable : examTimeTables) {
            DateTime start = new DateTime(examTimeTable.getStart());
            DateTime end = new DateTime(examTimeTable.getEnd());
            stringBuffer.append("科目：").append(examTimeTable.getCourse().getName()).append('\n');
            stringBuffer.append("时间：").append(start.getYear()).append("年").append(start.getMonthOfYear()).append("月")
                    .append(start.getDayOfMonth()).append("日 ")
                    .append(dateTimeHour(start)).append(dateTimeToText(start)).append(" - ").append(dateTimeToText(end)).append('\n');
            stringBuffer.append("地点：").append(examTimeTable.getCourse().getName()).append("\n\n");
        }
        stringBuffer.append("关注我们的另一个号【黑科校际plus】\n" +
                "回复【考试订阅】即可订阅考试通知\n" +
                "我们出了考试时间和考试前都会提醒你\n" +
                "信息仅供参考，以学校通知为准。");
        return new String(stringBuffer);
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
    private String dateTimeYear(DateTime dateTime) { // 判断是哪个学年
        int month = dateTime.getMonthOfYear();
        if (month > 2 && month < 9)
            return (dateTime.getYear() - 1) + "-" + dateTime.getYear();
        else
            return dateTime.getYear() + "-" + (dateTime.getYear() - 1);
    }

    private String dateTimeHour(DateTime dateTime) {
        if (dateTime.getHourOfDay() < 12)
            return "上午";
        else
            return "下午";
    }
    private String dateTimeTerm(DateTime dateTime){ // 判断是哪个学期
        int month = dateTime.getMonthOfYear();
        if(month > 2 && month < 9)
            return "2";
        else
            return "1";
    }

}
