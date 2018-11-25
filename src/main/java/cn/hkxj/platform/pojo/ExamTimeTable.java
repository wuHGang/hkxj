package cn.hkxj.platform.pojo;

import java.util.Date;

public class ExamTimeTable {
    private Integer id;

    private Room room;

    private Course course;

    private Integer year;

    private Integer term;

    private Integer schoolWeek;

    private Integer week;

    private Date start;

    private Date end;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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

    public Integer getSchoolWeek() {
        return schoolWeek;
    }

    public void setSchoolWeek(Integer schoolWeek) {
        this.schoolWeek = schoolWeek;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}