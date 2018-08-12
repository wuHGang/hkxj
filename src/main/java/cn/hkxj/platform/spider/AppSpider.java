package cn.hkxj.platform.spider;

import com.google.gson.Gson;
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
	private Integer account = null;
	private String passwd = null;
	private String token = null;
	@Value("${appspider.key}")
	private String key;
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

	public AppSpider() {

	}


	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() throws IOException {
		if (token != null)
			return token;

		RequestBody loginRequestBody = getLoginRequestBody();
		Request request = new Request.Builder()
				.url(login)
				.post(loginRequestBody)
				.build();

		Response response = client.newCall(request).execute();
		String result = response.body().string();

		Map data = (Map) result2Data(result);
		String token = (String) data.get("token");

		this.token = token;
		return token;
	}

	private RequestBody getLoginRequestBody() {
		HashMap<String, String> postData = new HashMap<String, String>();
		try {
			if (passwd == null)
				passwd = account.toString() + key;
			else
				passwd += key;
			postData.put("p", DigestUtils.md5Hex(passwd.getBytes("UTF-8")));
			postData.put("u", account.toString());
		} catch (UnsupportedEncodingException e) {
			log.error(e.toString());
		}
		String json = gson.toJson(postData);
		RequestBody requestBody = RequestBody.create(JSON, json);

		return requestBody;
	}

	private Object getData(String url) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.build();
		Response response = client.newCall(request).execute();
		String message = response.body().string();
		return result2Data(message);
	}

	public ArrayList getGrade() throws IOException {
		if (this.token == null)
			throw new RuntimeException("token is null");
		return (ArrayList) getGrade(this.token);
	}

	public ArrayList getGrade(String token) throws IOException {
		String url = grade + "?token=" + token;

		return (ArrayList) getData(url);
	}

	public ArrayList getLesson() throws IOException {
		if (this.token == null)
			throw new RuntimeException("token is null");
		return getLesson(this.token);
	}

	public ArrayList getLesson(String token) throws IOException {
		String url = lesson + "?token=" + token;

		return (ArrayList) getData(url);
	}

	public Map getSchedule() throws IOException {
		if (this.token == null)
			throw new RuntimeException("token is null");
		return getSchedule(this.token);
	}

	public Map getSchedule(String token) throws IOException {
		String url = schedule + "?token=" + token;

		return (Map) getData(url);
	}

	public ArrayList getExam() throws IOException {
		if (this.token == null)
			throw new RuntimeException("token is null");
		return getExam(this.token);
	}

	public ArrayList getExam(String token) throws IOException {
		String url = exam + "?token=" + token;

		return (ArrayList) getData(url);
	}

	private Object result2Data(String data) {
		Gson gson = new Gson();
		Map resultMap = gson.fromJson(data, Map.class);

		int state = ((Double) resultMap.get("state")).intValue();

		if (state != 200) {
			String msg = (String) resultMap.get("message");
			throw new RuntimeException(msg);
		}

		return resultMap.get("data");
	}

}
