package cn.hkxj.platform.pojo;


/**
 * @author flattery
 */
public class Exam {
	private String classname;   //对应的班级

	private String examName;    //课程名称

	private String classRoom;    //考试地点

	private String xn;      //考试年份

	private String examWeek;    //考试第几周

	private String examDay;     //星期几

	private String specificTime;    //考试的具体时间

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String getSpecificTime() {
		return specificTime;
	}

	public void setSpecificTime(String specificTime) {
		this.specificTime = specificTime;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getExamWeek() {
		return examWeek;
	}

	public void setExamWeek(String examWeek) {
		this.examWeek = examWeek;
	}

	public String getExamDay() {
		return examDay;
	}

	public void setExamDay(String examDay) {
		this.examDay = examDay;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Override
	public String toString() {
		return "Exam{" +
				"classname='" + classname + '\'' +
				", examName='" + examName + '\'' +
				", classRoom='" + classRoom + '\'' +
				", xn='" + xn + '\'' +
				", examWeek='" + examWeek + '\'' +
				", examDay='" + examDay + '\'' +
				", specificTime='" + specificTime + '\'' +
				'}';
	}
}
