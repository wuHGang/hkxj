package cn.hkxj.platform.pojo;

import lombok.Data;

import java.util.Comparator;
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
//            CourseTimeTable arr[] = new CourseTimeTable[10];
//            courseTimeTables.forEach(courseTimeTable -> {
//                arr[courseTimeTable.getOrder() / 2] = courseTimeTable;
//            });
            courseTimeTables.sort(Comparator.comparing(CourseTimeTable::getOrder));
            for(CourseTimeTable courseTimeTable : courseTimeTables){
                if(!Objects.equals(courseTimeTable, null)){
                    buffer.append("第").append(courseTimeTable.getOrder()).append("节")
                            .append("\n").append(courseTimeTable.getCourse().getName()).append("  ").append(courseTimeTable.getRoom().getName())
                            .append("\n").append("\n");
                }
            }
            return buffer.toString();
        }
        return null;
    }


}
