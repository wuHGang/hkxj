package cn.hkxj.platform.spider;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @author junrong.chen
 */
@Slf4j
class CaptchaBreaker {

    private static final String TENSORFLOW = "http://spider.hackerda.com/valid";


    private static final OkHttpClient client = new OkHttpClient.Builder()
            .followRedirects(false)
            .build();

    @Data
    private static final class Result{
        private int status;
        private String msg;
        private String data;
    }


    static String getCode(String key){
        String url = TENSORFLOW+"?key="+key;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();


        try {
            Response response = client.newCall(request).execute();
            Result result = JSON.parseObject(response.body().byteStream(), Result.class);
            System.out.println(result.toString());
            if(result.status == 200){
                return result.getData();
            }
            log.error("get code error {}", result.msg);
            throw new RuntimeException(result.msg);

        } catch (IOException e) {
            log.error("get code error", e);
            throw new RuntimeException(e);
        }



    }

    public static void main(String[] args) {
        System.out.println(getCode("4d7b1b74-a09b-46b5-942d-81a95fdc11ad"));
    }
}
