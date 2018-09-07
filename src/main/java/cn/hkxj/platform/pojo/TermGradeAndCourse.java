package cn.hkxj.platform.pojo;

import java.util.ArrayList;
import java.util.List;

public class TermGradeAndCourse {
    private List<GradeAndCourse> gradeAndCourseList;
    private String year;
    private int term;

    public TermGradeAndCourse(){
        gradeAndCourseList = new ArrayList<>();
    }

    public List<GradeAndCourse> getGradeAndCourseList() {
        return gradeAndCourseList;
    }

    public void setGradeAndCourseList(List<GradeAndCourse> gradeAndCourseList) {
        this.gradeAndCourseList = gradeAndCourseList;
    }

    public void addGradeAndCourse(GradeAndCourse gradeAndCourse){
        gradeAndCourseList.add(gradeAndCourse);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
