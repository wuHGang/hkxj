package cn.hkxj.platform.pojo;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/11/6 18:48
 */
@Data
public class CourseGroupMsg {

    private Classes classes;

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
                            .append("\n").append(arr[i].getCourseObject().getName()).append("  ").append(arr[i].getRoom().getName())
                            .append("\n").append("\n");
                }
            }
            return buffer.toString();
        }
        return null;
    }


}
