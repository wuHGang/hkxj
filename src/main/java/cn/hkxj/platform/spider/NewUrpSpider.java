package cn.hkxj.platform.spider;

import com.google.common.io.Files;
import okhttp3.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NewUrpSpider {
    private static final String ROOT = "http://xsurp.usth.edu.cn";
    private static final String CAPTCHA = ROOT + "/img/captcha.jpg";
    private static final String CHECK = ROOT + "/j_spring_security_check";
    private static final String INDEX = ROOT + "/index.jsp";
    private static final CookieManager cookieManager = new CookieManager();
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .cookieJar(new JavaNetCookieJar(cookieManager))
            .build();

    public void getCaptcha(){

        Request request = new Request.Builder()
                .url(CAPTCHA)
                .get()
                .build();

        try {
            byte[] bytes = client.newCall(request).execute().body().bytes();
            Files.write(bytes, new File("pic.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
        System.out.println(cookies);
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
            List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
            System.out.println(cookies);
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
        NewUrpSpider spider = new NewUrpSpider();
        spider.getCaptcha();
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        System.out.println(code);
        spider.studentCheck("学号", "密码", code);
//        spider.login();
    }
}
