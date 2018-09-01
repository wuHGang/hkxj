package cn.hkxj.platform.spider;

import cn.hkxj.platform.utils.ReadProperties;
import com.google.gson.Gson;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author JR Chan
 * @date 2018/6/4 21:06
 */

@Slf4j
@Component
public class AppSpider {
	/**
	 * 从APP接口上获取token，提供获取成绩，考试安排以及课程表三个方法
	 * 使用的时候需要先设置账号或者token，如果直接设置了token就不需要再获取。getToken会自动把token写进实例变量中。
	 * 获取数据时可以填入指定token作参数，不填入默认使用初始化生成token。
	 * 如果有密码错误异常，如果能设置密码需要自己声明
	 */
	private Integer account;
	private String password;
	private String token;
	private final static String key = ReadProperties.get("appspider.key");
	private final static Gson gson = new Gson();
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private final static String urlRoot = "http://222.171.107.108";
	private final static String login = urlRoot + "//university-facade/Murp/Login";
	private final static String grade = urlRoot + "//university-facade/MyUniversity/MyGrades";
	private final static String lesson = urlRoot + "//university-facade/MyUniversity/MyLessons";
	private final static String schedule = urlRoot + "//university-facade/Schedule/ScheduleList";
	private final static String exam = urlRoot + "//university-facade/MyUniversity/Exam";
	private final static OkHttpClient client = new OkHttpClient.Builder()
			.connectTimeout(5, TimeUnit.SECONDS)
			.build();

	private final static Headers headers = new Headers.Builder()
			.add("Host", "222.171.107.108")
			.add("Connection", "Keep-Alive")
			.add("Accept-Encoding", "gzip")
			.add("User-Agent", "okhttp/3.3.1")
			.build();

	AppSpider() {

	}

	public AppSpider(Integer account) {
		this.account = account;
		this.password = account.toString()+key;
	}

	public AppSpider(Integer account, String password) {
		this.account = account;
		this.password = password+key;
	}

	public AppSpider(@NonNull String token){
		this.token = token;
	}

	public String getToken() throws IOException {
		if (token != null)
			return token;

		RequestBody loginRequestBody = getLoginRequestBody();
		Map data = postData(login, loginRequestBody);
		Map data2=(Map) data.get("data");
		String token = (String) data2.get("token");
		this.token = token;

		return token;
	}

	public ArrayList getGrade() throws IOException {
		String url = grade + "?token=" + token;
		Map data = getData(url);

		return (ArrayList) data.get("data");
	}

	public ArrayList getLesson() throws IOException {
		String url = lesson + "?token=" + token;
		Map data = getData(url);
		return (ArrayList) data.get("data");
	}

	public Map getSchedule() throws IOException {
		String url = schedule + "?token=" + token;
		Map data = getData(url);
		return (Map) data.get("data");
	}

	public ArrayList getExam() throws IOException {
		String url = exam + "?token=" + token;
		Map data = getData(url);
		return (ArrayList) data.get("data");
	}


	private RequestBody getLoginRequestBody() {
		HashMap<String, String> postData = new HashMap<>();
		try {
			postData.put("u", account.toString());
			postData.put("p", DigestUtils.md5Hex(password.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}
		String json = gson.toJson(postData);

		return RequestBody.create(JSON, json);
	}

	private Map getData(String url) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.headers(headers)
				.build();
		return execute(request);
	}

	private Map postData(String url, RequestBody requestBody) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.headers(headers)
				.post(requestBody)
				.build();
		return execute(request);

	}

	private Map execute(Request request) throws IOException {
		Response response = client.newCall(request).execute();
		String data = response.body().string();

		Map resultMap = gson.fromJson(data, Map.class);

		int state = ((Double) resultMap.get("state")).intValue();

		if (state != 200) {
			String msg = (String) resultMap.get("message");
			throw new IOException(msg);
		}

		return resultMap;
	}

}
