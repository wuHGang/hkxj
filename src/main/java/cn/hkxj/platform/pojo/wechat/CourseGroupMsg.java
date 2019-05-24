package cn.hkxj.platform.pojo.wechat;

import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;

import java.util.*;

/**
 * @author Yuki
 * @date 2018/11/6 18:48
 */
public class CourseGroupMsg {

    private Classes classes;

    private List<CourseTimeTable> courseTimeTables;

    private Set<ScheduleTask> scheduleTasks;

    public CourseGroupMsg() {
        courseTimeTables = new ArrayList<>();
        scheduleTasks = new HashSet<>();
    }

    public String getCourseContent(){
        if(!Objects.equals(courseTimeTables, null)){
            StringBuffer buffer = new StringBuffer();
            courseTimeTables.sort(Comparator.comparing(CourseTimeTable::getOrder));
            for(CourseTimeTable courseTimeTable : courseTimeTables){
                if(!Objects.equals(courseTimeTable, null)){
                    buffer.append("第").append(courseTimeTable.getOrder()).append("节")
                            .append("\n").append(courseTimeTable.getCourse().getName()).append("\n").append(courseTimeTable.getRoom().getName())
                            .append("\n").append("\n");
                }
            }
            return buffer.toString();
        }
        return null;
    }


    public Classes getClasses() {
        return this.classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public List<CourseTimeTable> getCourseTimeTables() {
        return this.courseTimeTables;
    }

    public void setCourseTimeTables(List<CourseTimeTable> courseTimeTables) {
        this.courseTimeTables = courseTimeTables;
    }

    public Set<ScheduleTask> getScheduleTasks() {
        return scheduleTasks;
    }

    public void setScheduleTasks(Set<ScheduleTask> scheduleTasks) {
        this.scheduleTasks = scheduleTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseGroupMsg that = (CourseGroupMsg) o;
        return com.google.common.base.Objects.equal(classes, that.classes) &&
                com.google.common.base.Objects.equal(courseTimeTables, that.courseTimeTables) &&
                com.google.common.base.Objects.equal(scheduleTasks, that.scheduleTasks);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(classes, courseTimeTables, scheduleTasks);
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("classes", classes)
                .add("courseTimeTables", courseTimeTables)
                .add("scheduleTasks", scheduleTasks)
                .toString();
    }
}
