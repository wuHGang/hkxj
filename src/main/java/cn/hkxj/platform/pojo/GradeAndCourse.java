package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;

/**
 * @author JR Chan
 * @date 2018/12/15
 */
public class GradeAndCourse {
    private Grade grade;
    private Course course;

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("grade", grade)
                .add("course", course)
                .toString();
    }
}
