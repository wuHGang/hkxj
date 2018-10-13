package cn.hkxj.platform.utils;

import cn.hkxj.platform.pojo.Student;
import com.google.gson.Gson;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.HashMap;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
public class JsonUtils {
	private static final Gson gson = new Gson();

	public static String wxToJson(Object obj) {
		return WxMpGsonBuilder.create().toJson(obj);
	}

	public static String reponseToJson(Integer statu, String message){
		HashMap<Integer, String> map = new HashMap<>(1);
		map.put(statu, message);
		
		return gson.toJson(map);
	}
}
