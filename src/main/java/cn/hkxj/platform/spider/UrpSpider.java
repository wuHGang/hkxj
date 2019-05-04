package cn.hkxj.platform.spider;

import cn.hkxj.platform.spider.model.CurrentGrade;
import cn.hkxj.platform.spider.model.EverGrade;
import cn.hkxj.platform.spider.model.Information;
import cn.hkxj.platform.spider.model.UrpResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 教务网爬虫  由flask提供服务
 * @author JR Chan
 */


@Slf4j
public class UrpSpider {
    private int account;
	private String password;
    private static final String INFORMATION_URL = "http://spider.hackerda.com/apiV2/information";
    private static final String GRADE_URL = "http://spider.hackerda.com/apiV2/grade";
	private static final String CURRENT_GRADE_URL = GRADE_URL+"/current";
	private static final String EVER_GRADE_URL = GRADE_URL+"/ever";
    private static final String TEMP_EVER_GRADE_URL = GRADE_URL+"/ever/temp";
    private static final TypeReference<UrpResult<Information>> informationTypeReference
            = new TypeReference<UrpResult<Information>>() {
    };
    private static final TypeReference<UrpResult<CurrentGrade>> currentTypeReference
            = new TypeReference<UrpResult<CurrentGrade>>() {
    };
    private static final TypeReference<UrpResult<EverGrade>> everTypeReference
            = new TypeReference<UrpResult<EverGrade>>() {
    };
	private static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(4, TimeUnit.SECONDS)
            .writeTimeout(4, TimeUnit.SECONDS)
            .connectTimeout(4, TimeUnit.SECONDS)
			.build();

    public UrpSpider(int account, String password) {
		this.account = account;
		this.password = password;
	}

    public UrpResult<Information> getInformation() {

        String result = getResult(INFORMATION_URL);
        return JSON.parseObject(result, informationTypeReference);
    }

    @SuppressWarnings("return null")
    public UrpResult getGrade() {
        String result = getResult(GRADE_URL);
        return null;
    }

    public UrpResult<CurrentGrade> getCurrentGrade() {
        String result = getResult(CURRENT_GRADE_URL);

        return JSON.parseObject(result, currentTypeReference);
    }

    public UrpResult<EverGrade> getEverGrade() {
		String result = getResult(TEMP_EVER_GRADE_URL);

        return JSON.parseObject(result, everTypeReference);
    }

    private String getResult(String url) {
        String getURL = url + "?account=" + String.valueOf(this.account) + "&password=" + this.password;
        Request request = new Request.Builder()
                .url(getURL)
                .get()
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

        return result;
	}

}

