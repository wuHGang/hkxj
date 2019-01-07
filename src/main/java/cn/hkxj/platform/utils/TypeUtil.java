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
		return Integer.parseInt(value);
		//原句是return Integer.parseInt(value+"0"); 不知道是你们是特意这么写还是手误，导致学分变成3位数，如果是特意的请自己更改一下
	}

	public static int gradeToInt(String value){
        if (value.startsWith("优")) {
            return 90;
        }
        if (value.startsWith("良")) {
            return 80;
        }
        if (value.startsWith("中")) {
            return 70;
        }
        if (value.startsWith("及格")) {
            return 60;
        }
		if(value.contains(POINT)){
			int index = value.indexOf(POINT);
			value = value.substring(0, index) + value.substring(index +1, index+2);
		}
		return Integer.parseInt(value+"0");
	}
}
