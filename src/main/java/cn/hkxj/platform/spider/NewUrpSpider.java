package cn.hkxj.platform.spider;

import cn.hkxj.platform.spider.model.VerifyCode;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.slf4j.MDC;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NewUrpSpider {
    private static final String ROOT = "http://xsurp.usth.edu.cn";
    private static final String CAPTCHA = ROOT + "/img/captcha.jpg";
    private static final String CHECK = ROOT + "/j_spring_security_check";
    private static final String INDEX = ROOT + "/index.jsp";
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS)
            .cookieJar(new UrpCookieJar())
            .build();

    private final static Headers HEADERS = new Headers.Builder()
            .add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
            .add("Host", "xsurp.usth.edu.cn")
            .add("Connection", "keep-alive")
            .add("Accept-Encoding", "gzip")
            .add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
            .add("Upgrade-Insecure-Requests", "1")
            .build();

    public VerifyCode getCaptcha(){
        Request request = new Request.Builder()
                .url(CAPTCHA)
                .get()
                .headers(HEADERS)
                .build();

        try {
            if(client.newCall(request).execute().isSuccessful()){
                byte[] bytes = client.newCall(request).execute().body().bytes();
                return new VerifyCode(bytes);
            }

        } catch (IOException e) {
            log.error("get verify code error", e);
        }

        return null;
    }


    public void studentCheck(String account, String password, String captcha){
        FormBody.Builder params = new FormBody.Builder();
        FormBody body = params.add("j_username", account)
                .add("j_password", password)
                .add("j_captcha", captcha)
                .build();

        Request request = new Request.Builder()
                .url(CHECK)
                .post(body)
                .build();


        try {
            String string = client.newCall(request).execute().body().string();
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void login(){
        Request request = new Request.Builder()
                .url(INDEX)
                .get()
                .build();

        try {
            String string = client.newCall(request).execute().body().string();
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MDC.put("cookieTrace", "trace");
        NewUrpSpider spider = new NewUrpSpider();
        VerifyCode captcha = spider.getCaptcha();
        captcha.write("pic.jpg");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        System.out.println(code);
        spider.studentCheck("2014025838", "1", code);
//        spider.login();
    }
}
