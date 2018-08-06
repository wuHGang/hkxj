package cn.hkxj.platform.pojo;

import java.util.List;

/**
 * @author Yuki
 * @date 2018/7/14 12:31
 */
public class GradeDTO {

	private List<Grade> gradeList; //成绩列表

	private String xn;  //学年

	private String xq;  //该学年的第几学期

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	@Override
	public String toString() {
		return "GradeDTO{" +
				"gradeList=" + gradeList +
				", xn='" + xn + '\'' +
				", xq='" + xq + '\'' +
				'}';
	}
}
