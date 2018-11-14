package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import java.util.*;

/**
 * @author Yuki
 * @date 2018/11/6 18:48
 */
@Data
public class CourseGroupMsg {

    private String classname;

    private List<CourseTimeTable> courseTimeTables;

    private List<String> openIds;

    public String getCourseContent(){
        if(!Objects.equals(courseTimeTables, null)){
            StringBuffer buffer = new StringBuffer();
            CourseTimeTable arr[] = new CourseTimeTable[10];
            courseTimeTables.forEach(courseTimeTable -> {
                arr[courseTimeTable.getOrder() / 2] = courseTimeTable;
            });
            for(int i = 0; i < 4; i++){
                if(!Objects.equals(arr[i], null)){
                    buffer.append("第").append(arr[i].getOrder()).append("节")
                            .append("\n").append(arr[i].getCourseObject().getName()).append("  ").append(arr[i].getPosition())
                            .append("\n").append("\n");
                }
            }
            return buffer.toString();
        }
        return null;
    }


}
