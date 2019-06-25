package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author JR Chan
 * @date 2018/12/15
 */
@AllArgsConstructor
@NoArgsConstructor
public class GradeAndCourse {
    private Grade grade;
    private Course course;
    private Term term;

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

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradeAndCourse that = (GradeAndCourse) o;

        return Objects.equal(this.grade, that.grade) &&
                Objects.equal(this.course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(grade, course);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("grade", grade)
                .add("course", course)
                .add("term", term)
                .toString();
    }
}
