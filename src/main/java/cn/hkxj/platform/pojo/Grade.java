package cn.hkxj.platform.pojo;

/**
 * @author Yuki
 * @date 2018/7/14 12:20
 */
public class Grade {

	private String type;    //类型分为任选和必修

	private String courseName;  //课程名称

	private Double xf;   //学分

	private Double jd;   //绩点

	private Double cj;   //成绩

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Double getXf() {
		return xf;
	}

	public void setXf(Double xf) {
		this.xf = xf;
	}

	public Double getJd() {
		return jd;
	}

	public void setJd(Double jd) {
		this.jd = jd;
	}

	public Double getCj() {
		return cj;
	}

	public void setCj(Double cj) {
		this.cj = cj;
	}

	@Override
	public String toString() {
		return "Grade{" +
				"type='" + type + '\'' +
				", courseName='" + courseName + '\'' +
				", xf=" + xf +
				", jd=" + jd +
				", cj=" + cj +
				'}';
	}
}
