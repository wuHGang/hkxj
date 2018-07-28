package cn.hkxj.platform.pojo;

/**
 * @author Yuki
 * @date 2018/7/14 11:50
 */
public class Lesson {

    private String courseName;  //课程名称

    private String week;        //课程开始和结束的周数

    private String day;         //星期几上课

    private String specific;    //当天的第几节课

    private String classroom;    //上课地点

    private String teacher;     //讲课教师

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String specific) {
        this.specific = specific;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "courseName='" + courseName + '\'' +
                ", week='" + week + '\'' +
                ", day='" + day + '\'' +
                ", specific='" + specific + '\'' +
                ", classroom='" + classroom + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
