package cn.hkxj.platform.spider;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UrpSpider {
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private int account;
	private String password;
	private final static Gson GSON = new Gson();
	private static final String INFORMATION_URL = "http://spider.hackerda.com/information";
	private static final String GRADE_URL = "http://spider.hackerda.com/grade";
	private static final String CURRENT_GRADE_URL = GRADE_URL+"/current";
	private static final String EVER_GRADE_URL = GRADE_URL+"/ever";
	private static OkHttpClient client = new OkHttpClient.Builder()
			.connectTimeout(15, TimeUnit.SECONDS)
			.build();

    public UrpSpider(int account, String password) {
		this.account = account;
		this.password = password;
	}

    public Map getInformation() throws PasswordUncorrectException, ReadTimeoutException {
		Map result;
        log.info("urp spider start get student info account{}", this.account);
        result = getResult(INFORMATION_URL);

        Object information = result.get("information");
		if (Objects.isNull(information)){
			log.info("read school server timeout account{}", this.account);
			throw new ReadTimeoutException("学校服务器读取超时");
		}

        return (Map) information;
    }

    public Map getGrade() throws PasswordUncorrectException {
		return getResult(GRADE_URL);
	}

    public Map getCurrentGrade() throws PasswordUncorrectException {
        return getResult(CURRENT_GRADE_URL);
    }

    public void getEverGrade() throws PasswordUncorrectException {
		Map result = getResult(EVER_GRADE_URL);
	}

    private Map getResult(String url) throws PasswordUncorrectException {
		RequestBody requestBody = getRequestBody();
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.build();
        String result;
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            log.error("urp spider execute error {}", e.getMessage());
            throw new RuntimeException(e);
        }

		log.debug(result);

		HashMap resultMap = GSON.fromJson(result, HashMap.class);
		Double statu = (Double) resultMap.get("statu");

		if(statu.intValue() == 400) {
			log.info("password uncorrect account{} password{}", account, password);
			throw new PasswordUncorrectException("账号: "+account+"  密码："+password);
		}
		else if(statu.intValue() == 500) {
			throw new ReadTimeoutException("学校服务器连接超时");
		}
		return resultMap;
	}

	private RequestBody getRequestBody() {
		HashMap<String, String> postData = Maps.newHashMapWithExpectedSize(2);
        postData.put("account", String.valueOf(account));
		postData.put("password", password);
		String json = GSON.toJson(postData);

		return RequestBody.create(JSON, json);
	}

}
