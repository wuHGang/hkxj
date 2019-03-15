package cn.hkxj.platform.spider;

import cn.hkxj.platform.pojo.Academy;
import cn.hkxj.platform.pojo.Course;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author xie
 * @date 2018/11/16
 */
@Slf4j
public class UrpCourseSpider {
    //更新后课程添加了学院属性
    //现有的M黑科技爬虫和Urp爬虫都无法返回完整的课程和学院信息
    //UrpCourseSpider这个爬虫通过
    //http://60.219.165.24/kcxxAction.do?oper=kcxx_if&kch=课程号
    //这个接口来获取完整课程的学院信息
    //后期会整合进urpSpider中
    private String account;
    private String password;
    private String uid;
    private String loginUrl="http://60.219.165.24/loginAction.do";
    private String courseInformatiomUrl="http://60.219.165.24/kcxxAction.do?oper=kcxx_if&kch=";
    private static String creditRgex = "学分:</td><tdwidth=\"3\"height=\"18\"></td><tdcolspan=\"5\">.*?</td>";
    private static String academyRgex ="开课院系:</td><tdwidth=\"3\"></td><td>.*?</td>";
    private static String courseNameRgex="课程名:</td><td width=\"3\" height=\"18\">&nbsp;</td><td colspan=\"5\">.*?</td>";
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .build();

    public UrpCourseSpider(int account, String password){
        this.account = Integer.toString(account);
        this.password = password;
    }

    //获取课程的学院信息
    public Academy getAcademyId(String uid) {
        Pattern pattern = Pattern.compile(academyRgex);
        Matcher matcher = pattern.matcher(getCourseResult(uid));
        if (matcher.find()) {
            String a=StringUtils.substringBetween(matcher.group(),"</td><tdwidth=\"3\"></td><td>","</td>");
            return Academy.getAcademyByName(a);
        }
        log.error("course uid:{} can`t find academy", uid);
        throw new IllegalArgumentException("can`t find academy uid: " + uid);
    }

    //获取学院的名称信息
    public String getCourseName(String uid){
        Pattern pattern=Pattern.compile(courseNameRgex);
        Matcher matcher=pattern.matcher(getCourseResult(uid));
        if(matcher.find()){
            String courseName=StringUtils.substringBetween(matcher.group(),"课程名:</td><td width=\"3\" height=\"18\">&nbsp;</td><td colspan=\"5\">","</td>");
            return courseName;
        }
        log.error("course uid:{} can`t find courseName", uid);
        throw new IllegalArgumentException("can`t find courseName uid: " + uid);
    }

    private String getCourseResult(String uid) {
        this.uid=uid;
        FormBody formBody = getFormBody(account,password);
        Request request = new Request.Builder()
                .url(loginUrl)
                .post(formBody)
                .build();
        Response response ;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("get course error, course id {}", uid);
            throw new RuntimeException(e);
        }
        String cookie=response.headers().get("Set-Cookie").substring(0, response.headers().get("Set-Cookie").indexOf(";"));
        request = new Request.Builder()
                .url(courseInformatiomUrl+uid)
                .post(formBody)
                .addHeader("Cookie",cookie)
                .build();
        String result ;
        try {
            result = client.newCall(request).execute().body().string().replaceAll("\\s*", "");
        } catch (IOException e) {
            log.error("get course error, course id {}", uid);
            throw new RuntimeException(e);
        }
        response.body().close();
        return result;
    }

    private FormBody getFormBody(String account,String password) {
        FormBody formBody = new FormBody.Builder()
                .add("zjh", account)
                .add("mm", password)
                .build();
        return formBody;
    }
}
