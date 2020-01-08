package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.UrpCourse;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.spider.newmodel.searchclass.ClassCourseHour;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

@Slf4j
@Component
public class CourseRankHandler implements WxMpMessageHandler {
    @Resource
    private TextBuilder textBuilder;
    @Resource
    private OpenIdService openIdService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    private final static String template = "全校平均学时: 322.91  中位数学时:408\n\n你的班级:%s  总课时:%d  \n\n在全校的780个班级中排行第%s " +
            "\n击败了%s的班级";

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
        String appId = wxMpService.getWxMpConfigStorage().getAppId();
        String openid = wxMpXmlMessage.getFromUser();
        Student student = openIdService.getStudentByOpenId(openid, appId);

        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        String urpClassCode = hash.get(RedisKeys.URP_CLASS_CODE.getName(), student.getClasses().toString());

        if(StringUtils.isEmpty(urpClassCode)){
            return textBuilder.build("没有查询到你的排名", wxMpXmlMessage, wxMpService);
        }
        ClassCourseHour courseHour = JSON.parseObject(hash.get(RedisKeys.CLASS_COURSE_HOUR_DETAIL.getName(), urpClassCode), ClassCourseHour.class);
        if(courseHour == null){
            return textBuilder.build("没有查询到你的排名", wxMpXmlMessage, wxMpService);
        }

        ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();
        Long rank = zSet.reverseRank(RedisKeys.CLASS_COURSE_HOUR_RANK.getName(), urpClassCode);
        String percent;
        if(rank == 1){
            percent = "100%";
        }else if(rank == 780){
            percent = "0%";
        }else {
            percent = percent((780 - rank),780);
        }
        String result = String.format(template, courseHour.getClassInfo().getClassName(), courseHour.getCourseHourCount(),
                rank, percent);
        StringBuffer buffer = new StringBuffer(result);
        ArrayList<UrpCourse> courses = new ArrayList<>(courseHour.getUrpCourseSet());
        courses.sort(Comparator.comparingInt(UrpCourse::getClassHour).reversed());
        buffer.append(String.format("\n\n本学期一共有%d节课", courses.size())).append("\n\n");

        int count = 1;
        for (UrpCourse course : courses) {
            buffer.append(count).append("  ").append(course.getCourseName()).append("  ").append("学时:").append(course.getClassHour()).append("\n");
            count ++;
        }
        buffer.append("\n").append("回复【订阅】可以比78%的黑科大人" +
                "提前知道课表、考试成绩、考试时间。");


        return textBuilder.build(buffer.toString(), wxMpXmlMessage, wxMpService);
    }


    private static String percent(double d, double e){
        double p = d/e;
        DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();
        nf.applyPattern("00%"); //00表示小数点2位
        nf.setMaximumFractionDigits(2); //2表示精确到小数点后2位
        return nf.format(p);
    }

}
