package cn.hkxj.platform.spider;

import cn.hkxj.platform.pojo.constant.Academy;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


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
    private final static Gson GSON = new Gson();
    private String account;
    private String password;
    private String uid;
    private String loginUrl="http://60.219.165.24/loginAction.do";
    private String courseInformatiomUrl="http://60.219.165.24/kcxxAction.do?oper=kcxx_if&kch=";

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .build();

    public UrpCourseSpider(int account, String password){
        this.account = Integer.toString(account);
        this.password = password;
    }

    private FormBody getFormBody(String account,String password) {
        FormBody formBody = new FormBody.Builder()
                .add("zjh", account)
                .add("mm", password)
                .build();
        return formBody;
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
            result = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            log.error("get course error, course id {}", uid);
            throw new RuntimeException(e);
        }
        response.body().close();
        return result;
    }

    //小程序获取openid
    public Map getAppJson(String js_code)throws IOException{
        String app="https://api.weixin.qq.com/sns/jscode2session?appid=wx05f7264e83fa40e9&secret=a6053643e57616937b876d69bb080fa7&js_code="+js_code+"&grant_type=authorization_code";
        Request request=new Request.Builder()
                .url(app)
                .get()
                .build();
            Response response=client.newCall(request).execute();
            Map result=GSON.fromJson(response.body().string(),Map.class);
        return result;
    }

    //jsoup解析页面
    private Map getResultMap(){
        Map<String,String> infoMap=new HashMap<>();
        getCourseResult(uid);
        Document doc = Jsoup.parse(getCourseResult(uid));
        Elements tables  = doc.getElementsByClass("titleTop3");
        Element table=tables.get(0);
        tables=table.select("table");
        Element baseInfoTable=tables.get(1);
        Elements courseInfoList = baseInfoTable.select("tr");
        for(int i = 0; i < courseInfoList.size(); ++i){
            Element tr = courseInfoList.get(i);
            Elements tds = tr.select("td");
            infoMap.put(tds.get(0).text(),tds.get(2).text());
        }
        System.out.println(infoMap);
        return infoMap;
    }

    //获取课程的学院信息
    public Academy getAcademyId(String uid) {
        Map infoMap=getResultMap();
        try {
            return Academy.getAcademyByName((String) infoMap.get("开课院系"));
        }catch (Exception e){
            log.error("course uid:{} can`t find academy", uid);
            throw new IllegalArgumentException("can`t find academy uid: " + uid);
        }
    }

    //获取课程的名称信息
    public String getCourseName(String uid){
        Map infoMap=getResultMap();
        try {
            return (String) infoMap.get("课程名");
        }catch (Exception e){
            log.error("course uid:{} can`t find courseName", uid);
            throw new IllegalArgumentException("can`t find courseName uid: " + uid);
        }
    }

    //获取课程的学分信息
    public int getCourseCredit(String uid){
        Map infoMap=getResultMap();
        try {
            return (int) infoMap.get("学分");
        }catch (Exception e){
            log.error("course uid:{} can`t find courseCredit", uid);
            throw new IllegalArgumentException("can`t find courseCredit uid: " + uid);
        }
    }

}
