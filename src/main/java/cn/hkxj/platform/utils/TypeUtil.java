package cn.hkxj.platform.utils;

/**
 * @author junrong.chen
 * @date 2018/9/15
 */
public class TypeUtil {
	private static final String POINT = ".";

	public static int pointToInt(double point){
		String value = String.valueOf(point);
		if(value.contains(POINT)){
			int index = value.indexOf(POINT);
			value = value.substring(0, index) + value.substring(index +1);
		}
		return Integer.parseInt(value+"0");
	}

	public static int gradeToInt(String value){
		if(value.contains(POINT)){
			int index = value.indexOf(POINT);
			value = value.substring(0, index) + value.substring(index +1, index+2);
		}
		return Integer.parseInt(value+"0");
	}
}
