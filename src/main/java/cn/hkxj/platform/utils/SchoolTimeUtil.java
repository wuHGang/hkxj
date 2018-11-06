package cn.hkxj.platform.utils;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 * @author junrong.chen
 * @date 2018/10/31
 */
public class SchoolTimeUtil {
	/**
	 * 开学日期
	 */
	private static final DateTime TERM_START = new DateTime(2018, 8, 24, 0, 0);

	/**
	 * 教学周
	 */
	public static int getSchoolWeek(){
		return (int) new Duration(TERM_START, new DateTime()).getStandardDays() / 7 + 1;
	}

	/**
	 * 星期几
	 */
	public static int getDayOfWeek(){
		return new DateTime().getDayOfWeek();
	}

	/**
	 * 查询现在为第几节课
	 */
	public static int getOrderOfLesson(){
		return 0;
	}

	public static int getWeekDistinct() {
		 if(getSchoolWeek() % 2 == 0){
		 	return 2;
		 }
		 else {
		 	return 1;
		 }
	}

	public static void main(String[] args) {
		System.out.println(getSchoolWeek());
		System.out.println(getWeekDistinct());
	}
}
