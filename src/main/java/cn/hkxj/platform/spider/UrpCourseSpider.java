package cn.hkxj.platform.spider;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.Academy;
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
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .build();

    public UrpCourseSpider(int account, String password){
        this.account = Integer.toString(account);
        this.password = password;
    }

    //AcademyEnum匹配学院名字和id
    public int getAcademyId(String uid) throws IOException{
        int academyId=0;
        Pattern pattern = Pattern.compile(academyRgex);
        Matcher matcher = pattern.matcher(this.getResult(uid));
        while (matcher.find()) {
            String a=StringUtils.substringBetween(matcher.group(),"</td><tdwidth=\"3\"></td><td>","</td>");
            if(a.equals("机关"))
                academyId=20;
            else if (a.equals("体育部"))
                academyId=0;
            else academyId=Academy.getAcademyCodeByName(a);  //AcademyEnum匹配学院名字和id
        }
        return academyId;
    }

    private String getResult(String uid) throws IOException{
        this.uid=uid;
        FormBody formBody = getFormBody(account,password);
        Request request = new Request.Builder()
                .url(loginUrl)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        String cookie=response.headers().get("Set-Cookie").substring(0, response.headers().get("Set-Cookie").indexOf(";"));
        request = new Request.Builder()
                .url(courseInformatiomUrl+uid)
                .post(formBody)
                .addHeader("Cookie",cookie)
                .build();
        String result = client.newCall(request).execute().body().string().replaceAll("\\s*", "");
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
