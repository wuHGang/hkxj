package cn.hkxj.platform.service.spider;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JR Chan
 * @date 2018/6/4 21:06
 */
@Component
public class AppSpider {
    /**
     * 从APP接口上获取token，提供获取成绩，考试安排以及课程表三个方法
     * 使用的时候需要先设置账号或者token，如果直接设置了token就不需要再获取。getToken会自动把token写进实例变量中。
     * 获取数据时可以填入指定token作参数，不填入默认使用初始化生成token。
     * 如果有密码错误异常，如果能设置密码需要自己声明
     */
    private Integer account = null;
    private String passwd = null;
    private String token = null;
    @Value("${appspider.key}")
    private String key;
    private final static String urlRoot = "http://222.171.107.108";

    public AppSpider(){

    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() throws IOException {
        if (token != null)
            return token;

        String route = "//university-facade/Murp/Login";
        HttpRequest request = null;

        String url = urlRoot+route;

        try {
            request = new HttpRequest(url);
        } catch (IOException e) {
            throw new RuntimeException("url invalid");
        }
        HashMap<String, String> postData = new HashMap<String, String>();
        postData.put("u", account.toString());
        try {
            if (passwd == null)
                passwd = account.toString()+key;
            else
                passwd += key;
            postData.put("p", DigestUtils.md5Hex(passwd.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        String result = request.post(postData);

        Map data = (Map) result2Data(result);
        String token = (String) data.get("token");

        this.token = token;
        return token;
    }

    private Object getData(String url) throws IOException {
        HttpRequest request = null;
        try {
            request = new HttpRequest(url);

        } catch (IOException e) {
            throw new RuntimeException("url invalid");
        }

        return result2Data(request.get());
    }

    public ArrayList getGrade() throws IOException {
        if(this.token == null)
            throw new RuntimeException("token is null");
        return (ArrayList)getGrade(this.token);
    }

    public ArrayList getGrade(String token) throws IOException {
        String route = "//university-facade/MyUniversity/MyGrades";
        String url = urlRoot+route+"?token="+token;

        return (ArrayList) getData(url);
    }

    public ArrayList getLesson() throws IOException {
        if(this.token == null)
            throw new RuntimeException("token is null");
        return getLesson(this.token);
    }

    public ArrayList getLesson(String token) throws IOException {
        String route = "//university-facade/MyUniversity/MyLessons";
        String url = urlRoot+route+"?token="+token;

        return  (ArrayList) getData(url);
    }

    public Map getSchedule() throws IOException {
        if(this.token == null)
            throw new RuntimeException("token is null");
        return getSchedule(this.token);
    }

    public Map getSchedule(String token) throws IOException {
        String route = "//university-facade/Schedule/ScheduleList";
        String url = urlRoot+route+"?token="+token;

        return (Map) getData(url);
    }

    private Object result2Data(String data){
        Gson gson = new Gson();
        Map resultMap = gson.fromJson(data, Map.class);

        int state = ((Double)resultMap.get("state")).intValue();

        if (state != 200){
            String msg = (String) resultMap.get("message");
            throw new RuntimeException(msg);
        }

        return resultMap.get("data");
    }

}
