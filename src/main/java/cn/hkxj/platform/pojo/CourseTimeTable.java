package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;

public class CourseTimeTable {
    private Integer id;

    private Integer course;

    private Integer year;

    private Integer term;

    private Integer start;

    private Integer end;

    private Integer week;

    private Integer order;

    private Integer distinct;

    private Room room;

    private Course courseObject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getDistinct() {
        return distinct;
    }

    public void setDistinct(Integer distinct) {
        this.distinct = distinct;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Course getCourseObject() {
        return courseObject;
    }

    public void setCourseObject(Course courseObject) {
        this.courseObject = courseObject;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("course", course)
                .add("year", year)
                .add("term", term)
                .add("start", start)
                .add("end", end)
                .add("week", week)
                .add("order", order)
                .add("distinct", distinct)
                .add("room", room)
                .add("courseObject", courseObject)
                .toString();
    }
}