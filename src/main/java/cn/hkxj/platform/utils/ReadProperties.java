package cn.hkxj.platform.utils;

import java.util.ResourceBundle;

public class ReadProperties {
	private static final ResourceBundle resource = ResourceBundle.getBundle("application");

	public static String get(String key){
		return resource.getString(key);
	}

	public static void main(String[] args) {
		System.out.println(get("appspider.key"));
	}
}
