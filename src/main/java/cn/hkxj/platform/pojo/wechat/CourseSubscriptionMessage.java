package cn.hkxj.platform.pojo.wechat;

import cn.hkxj.platform.pojo.CourseTimeTableDetail;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.dto.CourseTimeTableDetailDto;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

/**
 * @author Yuki
 * @date 2019/10/16 10:57
 * 课程订阅推送消息
 */
@Data
public class CourseSubscriptionMessage {

    private ScheduleTask task;

    private Student student;
    /**
     * 因为课程表现在是和个人进行关联且推送是按节数来推送
     * 所以此处不需要集合
     */
    private CourseTimeTableDetailDto detailDto;

    private int section;

    public String getPushContent(){
        if(Objects.isNull(detailDto)) { return null; }
        return "第" + detailDto.getDetail().getOrder() + "节" +
                "\n" + detailDto.getUrpCourse().getCourseName() + "\n" + detailDto.getDetail().getRoomName();
    }
}
