package cn.hkxj.platform.utils;

import cn.hkxj.platform.pojo.Student;
import com.google.gson.Gson;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
public class JsonUtils {
	private static Gson gson = new Gson();

	public static String toJson(Object obj) {
		return WxMpGsonBuilder.create().toJson(obj);
	}

	public void jsonToClass(String json) {
		Student student = gson.fromJson(json, Student.class);
	}
}
