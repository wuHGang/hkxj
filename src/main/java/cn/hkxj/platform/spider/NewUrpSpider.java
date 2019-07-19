package cn.hkxj.platform.spider;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.UrpRequestException;
import cn.hkxj.platform.exceptions.UrpTimeoutException;
import cn.hkxj.platform.exceptions.UrpVerifyCodeException;
import cn.hkxj.platform.spider.model.UrpStudentInfo;
import cn.hkxj.platform.spider.model.VerifyCode;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.MDC;

import java.io.IOException;
import java.net.CookieManager;
import java.util.*;

@Slf4j
public class NewUrpSpider {
    private static final String ROOT = "http://222.171.146.55";
    private static final String CAPTCHA = ROOT + "/img/captcha.jpg";
    private static final String CHECK = ROOT + "/j_spring_security_check";
    private static final String LOGIN = ROOT + "/getStudentInfo";
    private static final String STUDENT_INFO = ROOT + "/student/rollManagement/rollInfo/index";
    private static final CookieManager manager = new CookieManager();
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .cookieJar(new UrpCookieJar())
            .retryOnConnectionFailure(true)
            .addInterceptor(new RetryInterceptor(5))
            .followRedirects(false)
            .build();

    private final static Headers HEADERS = new Headers.Builder()
            .add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
            .add("Host", "xsurp.usth.edu.cn")
            .add("Connection", "keep-alive")
            .add("Accept-Encoding", "gzip, deflate")
            .add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
            .add("Upgrade-Insecure-Requests", "1")
            .add("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
            .add("Cache-Control", "max-age=0")
            .build();


    private String account;
    private String password;

    public VerifyCode getCaptcha() {
        Request request = new Request.Builder()
                .url(CAPTCHA)
                .header("Referer", LOGIN)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36")
                .get()
                .build();

        return new VerifyCode(execute(request));

    }

    /**
     * 登陆校验
     */
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

        Response response = getResponse(request);
        String location = response.header("Location");

        if(StringUtils.isEmpty(location)){
            throw new UrpRequestException("url: "+ request.url().toString()+ " code: "+response.code()+" cause: "+ response.message());
        }else if(location.contains("badCaptcha")){
            throw new UrpVerifyCodeException();
        }else if(location.contains("badCredentials")){
            throw new PasswordUncorrectException();
        }

        this.account = account;
        this.password = password;

    }


    public UrpStudentInfo getStudentInfo(){
        if(this.account == null){
            throw new UnsupportedOperationException("spider haven`t login");
        }
        Request request = new Request.Builder()
                .url(STUDENT_INFO)
                .get()
                .build();

        Map<String, String> userInfo = parseUserInfo(new String(execute(request)));

        UrpStudentInfo student = new UrpStudentInfo();
        student.setAccount(Integer.parseInt(userInfo.get("学号")));
        student.setEthnic(userInfo.get("民族"));
        student.setSex(userInfo.get("性别"));
        student.setName(userInfo.get("姓名"));
        student.setMajor(userInfo.get("专业"));
        student.setAcademy(userInfo.get("学院"));

        return student;
    }

    private Map<String, String> parseUserInfo(String html){
        HashMap<String, String> infoMap = new HashMap<>();
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("profile-info-row");
        for(Element e: elements){
            Elements name = e.getElementsByClass("profile-info-name");
            List<Element> nameList = Lists.newArrayList(name.iterator());
            Elements value = e.getElementsByClass("profile-info-value");
            List<Element> valueList = Lists.newArrayList(value.iterator());


            for(int x=0; x< nameList.size(); x++){
                infoMap.put(nameList.get(x).text(), valueList.get(x).text());
            }
        }

        return infoMap;
    }



    private byte[] execute(Request request){
        try (Response response = client.newCall(request).execute()) {
            if(isResponseFail(response)){
                throw new UrpRequestException("url: "+ request.url().toString()+ " code: "+response.code()+" cause: "+ response.message());
            }
            return response.body().bytes();
        } catch (IOException e) {
            throw new UrpTimeoutException(request.url().toString(), e);
        }
    }


    private Response getResponse(Request request){
        try (Response response = client.newCall(request).execute()) {
            if(isResponseFail(response)){
                throw new UrpRequestException("url: "+ request.url().toString()+ " code: "+response.code()+" cause: "+ response.message());
            }
            return response;
        } catch (IOException e) {
            throw new UrpTimeoutException(request.url().toString(), e);
        }
    }


    /**
     *
     * @param response
     * @return
     */
    private boolean isResponseFail(Response response){
        return response.body() == null ||
                (!response.isSuccessful() && !response.isRedirect());
    }



    public static void main(String[] args) {
        MDC.put("cookieTrace", "trace");
        NewUrpSpider spider = new NewUrpSpider();
        VerifyCode captcha = spider.getCaptcha();
        captcha.write("pic.jpg");
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        spider.studentCheck("2014025838", "1", code);
        spider.getStudentInfo();
    }
}
