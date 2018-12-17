package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Date;

public class Grade {
    private Integer id;

    private Integer account;

    private String courseId;

    private Integer year;

    private Byte term;

    private Integer score;

    private Integer point;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Byte getTerm() {
        return term;
    }

    public void setTerm(Byte term) {
        this.term = term;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }


	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("account", account)
				.add("courseId", courseId)
				.add("year", year)
				.add("term", term)
				.add("score", score)
				.add("point", point)
				.add("gmtCreate", gmtCreate)
				.toString();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade that = (Grade) o;

        return Objects.equal(this.account, that.account) &&
                Objects.equal(this.year, that.year) &&
                Objects.equal(this.courseId, that.courseId) &&
                Objects.equal(this.term, that.term) &&
                Objects.equal(this.score, that.score) &&
                Objects.equal(this.point, that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(account, year, term, score,
                point, courseId);
    }
}