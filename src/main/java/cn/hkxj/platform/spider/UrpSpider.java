package cn.hkxj.platform.spider;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
import cn.hkxj.platform.pojo.Student;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UrpSpider {
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private String account;
	private String password;
	private final static Gson gson = new Gson();
	private static final String informationURL = "http://119.29.119.49:10086/information";
	private static final String gradeURL = "http://119.29.119.49:10086/grade";
	private static OkHttpClient client = new OkHttpClient.Builder()
			.connectTimeout(15, TimeUnit.SECONDS)
			.build();

	public UrpSpider(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public Student getInformaton() throws PasswordUncorrectException, ReadTimeoutException {
		Map result = null;
		try {
			result = getResult(informationURL);
		} catch (IOException e) {
			throw new ReadTimeoutException("本地服务器读取超时", e);
		}

		Map information = (Map)result.get("information");

		Student student = new Student();
		try {
			BeanUtils.populate(student, information);
		} catch (IllegalAccessException e) {
			log.error(e.toString());
		} catch (InvocationTargetException e) {
			log.error(e.toString());
		}

		return student;
	}

	public void getGrade() throws IOException, PasswordUncorrectException {
		getResult(gradeURL);
	}

	private Map getResult(String url) throws IOException, PasswordUncorrectException {
		RequestBody requestBody = getRequestBody();
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.build();
		Response response = client.newCall(request).execute();

		String result = response.body().string();

		HashMap resultMap = gson.fromJson(result, HashMap.class);
		Double statu = (Double) resultMap.get("statu");

		if(statu.intValue() == 400) {
			throw new PasswordUncorrectException("账号: "+account+"密码："+password);
		}
		else if(statu.intValue() == 500) {
			throw new ReadTimeoutException("学校服务器连接超时");
		}
		return resultMap;
	}

	private RequestBody getRequestBody() {
		HashMap<String, String> postData = new HashMap<String, String>();
		postData.put("account", account);
		postData.put("password", password);
		String json = gson.toJson(postData);

		return RequestBody.create(JSON, json);
	}

}
