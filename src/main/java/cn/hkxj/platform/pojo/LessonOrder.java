package cn.hkxj.platform.pojo;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

/**
 * @author junrong.chen
 * @date 2018/11/18
 */
public enum LessonOrder {
	/**
	 * 课程顺序对应的上课时间
	 */
	MORNING1(1,"上午第一节", new LocalTime(8, 30), new LocalTime(10, 5)),
	MORNING2(3,"上午第二节", new LocalTime(10, 5), new LocalTime(12, 10)),
	AFTERNOON1(5,"下午第一节", new LocalTime(13, 30), new LocalTime(14, 55)),
	AFTERNOON2(7,",上午第二节", new LocalTime(14, 55), new LocalTime(16, 35)),
	EVENING1(9,"晚上第一节", new LocalTime(18, 30), new LocalTime(20, 5));

	private int order;
	private String text;
	private LocalTime start;
	private LocalTime end;

	LessonOrder(int order, String text, LocalTime start, LocalTime end) {
		this.order = order;
		this.text = text;
		this.start = start;
		this.end = end;
	}

	public static LessonOrder getLessonOrder(LocalTime current) {
		if (current.isBefore(MORNING1.end)) {
			return MORNING1;
		}
		if(current.isBefore(MORNING2.end)) {
			return MORNING2;
		}
		if(current.isBefore(AFTERNOON1.end)) {
			return AFTERNOON1;
		}
		if(current.isBefore(AFTERNOON2.end)) {
			return AFTERNOON2;
		}
		return EVENING1;
	}


	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}

	enum Season{
		/**
		 * 区分学校的冬令时和夏令时
		 */
		SUMMER,
		WINTER
	}
}
