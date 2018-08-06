package cn.hkxj.platform.service.spider;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class UrpSpider {
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private String account;
	private String password;
	private final static Gson gson = new Gson();
	private static final String information = "http://119.29.119.49:10086/information";
	private static final String grade = "http://119.29.119.49:10086/grade";
	private static OkHttpClient client = new OkHttpClient.Builder()
			.connectTimeout(11, TimeUnit.SECONDS)
			.build();

	public UrpSpider(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public void getInformaton() throws IOException {
		String result = getResult(information);
		HashMap hashMap = gson.fromJson(result, HashMap.class);
		System.out.println(hashMap.get("information").toString());
	}

	public void getGrade() throws IOException {
		getResult(grade);
	}

	private String getResult(String url) throws IOException {
		RequestBody requestBody = getRequestBody();
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}

	private RequestBody getRequestBody() {
		HashMap<String, String> postData = new HashMap<String, String>();
		postData.put("account", account);
		postData.put("password", password);
		String json = gson.toJson(postData);

		return RequestBody.create(JSON, json);
	}

}
