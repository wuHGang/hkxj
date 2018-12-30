package cn.hkxj.platform.pojo;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/11/6 18:48
 */
public class CourseGroupMsg {

    private Classes classes;

    private List<CourseTimeTable> courseTimeTables;

    private List<String> openIds;

    public CourseGroupMsg() {
    }

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

    public List<String> getOpenIds() {
        return this.openIds;
    }

    public void setOpenIds(List<String> openIds) {
        this.openIds = openIds;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CourseGroupMsg)) return false;
        final CourseGroupMsg other = (CourseGroupMsg) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$classes = this.getClasses();
        final Object other$classes = other.getClasses();
        if (this$classes == null ? other$classes != null : !this$classes.equals(other$classes)) return false;
        final Object this$courseTimeTables = this.getCourseTimeTables();
        final Object other$courseTimeTables = other.getCourseTimeTables();
        if (this$courseTimeTables == null ? other$courseTimeTables != null : !this$courseTimeTables.equals(other$courseTimeTables))
            return false;
        final Object this$openIds = this.getOpenIds();
        final Object other$openIds = other.getOpenIds();
        if (this$openIds == null ? other$openIds != null : !this$openIds.equals(other$openIds)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CourseGroupMsg;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $classes = this.getClasses();
        result = result * PRIME + ($classes == null ? 43 : $classes.hashCode());
        final Object $courseTimeTables = this.getCourseTimeTables();
        result = result * PRIME + ($courseTimeTables == null ? 43 : $courseTimeTables.hashCode());
        final Object $openIds = this.getOpenIds();
        result = result * PRIME + ($openIds == null ? 43 : $openIds.hashCode());
        return result;
    }

    public String toString() {
        return "CourseGroupMsg(classes=" + this.getClasses() + ", courseTimeTables=" + this.getCourseTimeTables() + ", openIds=" + this.getOpenIds() + ")";
    }
}
