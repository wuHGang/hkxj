package cn.hkxj.platform.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ResourceBundle;

@Component
public class ReadProperties {

	private static String profile;

	private static ResourceBundle resource;

	public static String get(String key){
		return resource.getString(key);
	}

	@PostConstruct
	public void init(){
		resource = ResourceBundle.getBundle(profile);
	}

	@Value("${filename}")
	private void setProfile(String profile){
		ReadProperties.profile = profile;
	}

	public static void main(String[] args) {
		System.out.println(get("appspider.key"));
	}
}
