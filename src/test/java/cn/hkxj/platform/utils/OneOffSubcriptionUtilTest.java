package cn.hkxj.platform.utils;

import cn.hkxj.platform.PlatformApplication;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author Yuki
 * @date 2018/11/22 11:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
@TestPropertySource(value = "classpath:application-prod.properties")
public class OneOffSubcriptionUtilTest {


    @Test
    public void getOneOffSubscriptionUrl() throws UnsupportedEncodingException, FileNotFoundException {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Student student = new Student(2016024170, "1");


        RequestBody requestBody = FormBody.create(MediaType.parse("appliaction/json;charset=UTF-8"), JsonUtils.wxToJson(student));

        Request request = new Request.Builder()
                .url("http://localhost:8080/login/student")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });


//        System.out.println( OneOffSubcriptionUtil.getHyperlinks("点击领取课表", "1005"));
    }
}

class Student{

    int account;
    String password;

    Student(int account, String password){
        this.account = account;
        this.password = password;
    }
}