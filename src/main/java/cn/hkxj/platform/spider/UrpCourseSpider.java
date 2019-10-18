package cn.hkxj.platform.spider;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

;


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
        FormBody formBody = getFormBody(account,password);
        Request request = new Request.Builder()
                .url(loginUrl)
                .post(formBody)
                .build();
        Response response ;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("get course.json error, course.json id {}", uid);
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
            log.error("get course.json error, course.json id {}", uid);
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
    public UrpCourse getUrpCourse(String uid) {
        Document doc = Jsoup.parse(getCourseResult(uid));
        Elements tables  = doc.getElementsByClass("titleTop3");
        Element table = tables.get(0);
        tables = table.select("table");
        Element baseInfoTable = tables.get(1);
        Elements courseInfoList = baseInfoTable.select("tr");

        UrpCourse urpCourse = new UrpCourse();
        for (Element tr : courseInfoList) {
            Elements tds = tr.select("td");
            String result = StringUtils.deleteWhitespace(tds.get(2).text());
            if (result.equals(" ")) {
                result = StringUtils.EMPTY;
            }
            switch (tds.get(0).text()) {
                case "课程号:":
                    urpCourse.setUid(result);
                    break;
                case "学时:":
                    if (StringUtils.isNotEmpty(result)) {
                        urpCourse.setStudyTime(Double.parseDouble(result));
                    }
                    break;
                case "本研标志:":
                    urpCourse.setFlag(result);
                case "学分:":
                    if (StringUtils.isNotEmpty(result)) {
                        urpCourse.setCredit(Double.parseDouble(result));
                    }
                    break;
                case "英文课程名":
                    urpCourse.setEnglishName(result);
                    break;
                case "开课院系:":
                    urpCourse.setAcademyName(result);
                    break;
                case "开课学期:":
                    urpCourse.setStartTime(result);
                    break;
                case "课程名:":
                    urpCourse.setName(result);
                    break;
            }

        }
        log.debug(urpCourse.toString());
        return urpCourse;
    }

}
