package cn.hkxj.platform.pojo.wechat;

import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.dto.CourseTimeTableDetailDto;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Yuki
 * @date 2018/11/6 18:48
 */
@Data
public class CourseGroupMsg {

    private Classes classes;

    private List<CourseTimeTableDetailDto> detailDtos;

    private List<ScheduleTask> scheduleTasks;

    public CourseGroupMsg() {

    }

    public String getCourseContent(){
        StringBuilder builder = new StringBuilder();
        if(CollectionUtils.isEmpty(detailDtos)){
            builder.append("今天没有课呐，可以出去浪了~\n");
        } else {
            detailDtos.sort(Comparator.comparing(detailVo -> detailVo.getDetail().getOrder()));
            for(CourseTimeTableDetailDto detailVo : detailDtos){
                if(!Objects.equals(detailVo, null)){
                    builder.append("第").append(detailVo.getDetail().getOrder()).append("节")
                            .append("\n").append(detailVo.getUrpCourse().getCourseName()).append("\n").append(detailVo.getDetail().getRoomName())
                            .append("\n").append("\n");
                }
            }
        }
        return builder.toString();
    }


}
