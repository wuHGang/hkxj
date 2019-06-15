package cn.hkxj.platform.pojo.vo;

import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import lombok.Data;

/**
 * @author JR Chan
 * @date 2019/6/14
 */
@Data
public class RoomOrderTimeTableVO {
    private int order;
    private boolean empty = true;
    private CourseTimeTable courseTimeTable;
}
