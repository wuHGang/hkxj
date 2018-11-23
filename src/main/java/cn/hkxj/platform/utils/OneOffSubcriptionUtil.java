package cn.hkxj.platform.utils;

import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.pojo.CourseTimeTable;
import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.OpenidExample;
import cn.hkxj.platform.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.net.*;
import java.util.List;

/**
 * @author Yuki
 * @date 2018/11/22 10:44
 */
@Slf4j
@Component
public class OneOffSubcriptionUtil {

    @Resource
    private WxMpService wxMpService;
    @Resource
    private CourseService courseService;
    @Resource
    private OpenidMapper openidMapper;

    private static OneOffSubcriptionUtil util;

    @PostConstruct
    public void init() {
        util = this;
        util.wxMpService = this.wxMpService;
        util.courseService = this.courseService;
        util.openidMapper = this.openidMapper;
    }

    private static final String BASE_URL = "https://mp.weixin.qq.com/mp/subscribemsg?action=get_confirm";
    private static final String REDIRECT_URL = "http://test.mrbeen.cn/wechat/sub/test";
    private static final String TEMPLATE_ID = "5TgQ5wk_3q01xfdqAqPDgAJDiT4YfmYOoIP6cnAhOKc";
    private static final String REPLY_URL = "https://api.weixin.qq.com/cgi-bin/message/template/subscribe?access_token=";

    private static String appid;

    /**
     * 获取带有一次性订阅链接的超链接
     * @param content 超链接的文字内容
     * @param scene 要生成的一次性订阅连接中的场景值
     * @return  返回一个带有一次性订阅链接的超链接
     */
    public static String getHyperlinks(String content, String scene){
        return new StringBuilder().append("<a href='")
                .append(getOneOffSubscriptionUrl(scene))
                .append("'>").append(content).append("</a>").toString();
    }

    public static String getOneOffSubscriptionUrl(String scene) {
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_URL).append("&")
                .append("appid=").append(util.wxMpService.getWxMpConfigStorage().getAppId()).append("&")
                .append("scene=").append(scene).append("&")
                .append("template_id=").append(TEMPLATE_ID).append("&");
        try {
            builder.append("redirect_url=").append(URLEncoder.encode(REDIRECT_URL, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            builder.append("redirect_url=").append(URLEncoder.encode(REDIRECT_URL));
        }
        builder.append("#wechat_redirect");
        return builder.toString();
    }

    public static void sendTemplateMessageToUser(String openid, String scene) {
        try {
            replyOneOffSubscribeRequest(generateDataJson(openid, scene));
        } catch (WxErrorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void replyOneOffSubscribeRequest(String sendContent) throws WxErrorException, IOException {
        //建立链接
        URL url = new URL(getReplyUrl());
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        //设置参数
        httpURLConnection.setDoOutput(true);//需要输出
        httpURLConnection.setDoInput(true);//需要输入
        httpURLConnection.setRequestMethod("POST");

        //设置请求属性
        httpURLConnection.setRequestProperty("accept", "*/*");
        httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        log.info("send message content is {}", sendContent);
        PrintWriter writer = new PrintWriter(httpURLConnection.getOutputStream());
        writer.write(sendContent);
        writer.flush();
        writer.close();

        //获取相应状态
        int resultCode = httpURLConnection.getResponseCode();
        if (HttpURLConnection.HTTP_OK == resultCode) {
            StringBuilder builder = new StringBuilder();
            String readline = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
            while ((readline = reader.readLine()) != null) {
                builder.append(readline);
            }
            reader.close();
            System.out.println(builder.toString());
        }
    }

    public static String generateDataJson(String openid, String scene){
        return new StringBuilder().append("{")
                .append("\"touser\":\"").append(openid).append("\",")
                .append("\"template_id\":\"").append(TEMPLATE_ID).append("\",")
                .append("\"url\":\"").append(getOneOffSubscriptionUrl(scene)).append("\",")
                .append("\"scene\":\"").append(scene).append("\",")
                .append("\"title\":\"").append("今日课表").append("\",")
                .append("\"data\":{")
                .append("\"content\":{")
                .append("\"value\":\"").append(getMsgContent(openid)).append("\",")
                .append("\"color\":").append("\"black\"")
                .append("}").append("}").append("}").toString();
    }

    private static String getMsgContent(String openid) {
        OpenidExample example = new OpenidExample();
        example.createCriteria()
                .andOpenidEqualTo(openid);
        Openid openidObject = util.openidMapper.selectByExample(example).get(0);
        List<CourseTimeTable> courseTimeTables = util.courseService.getCoursesCurrentDay(openidObject.getAccount());
        return util.courseService.toText(courseTimeTables);
    }

    private static String getReplyUrl() throws WxErrorException {
        return REPLY_URL + util.wxMpService.getAccessToken();
    }

}
