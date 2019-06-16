package cn.hkxj.platform.utils;

import cn.hkxj.platform.pojo.wechat.OneOffSubscription;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Yuki
 * @date 2018/11/22 10:44
 */
@Slf4j
@Component
public class OneOffSubcriptionUtil {

    private static String domain;
    //发送一次性订阅信息链接的基础url
    private static final String SEND_BASE_URL = "https://mp.weixin.qq.com/mp/subscribemsg?action=get_confirm";
    //回复一次性订阅信息的基础url
    private static final String REPLY_BASE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/subscribe?access_token=";

    //创建一个okHttpClient
    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(4, TimeUnit.SECONDS)
            .writeTimeout(4, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .build();

    /**
     * 获取带有一次性订阅链接的超链接,当content为null直接返回链接
     * @param content 超链接的文字内容
     * @param scene 要生成的一次性订阅连接中的场景值
     * @return  返回一个带有一次性订阅链接的超链接
     */
    public static String getHyperlinks(String content, String scene, WxMpService wxMpService){
        if(Objects.isNull(content)){
            return getOneOffSubscriptionUrl(scene, wxMpService);
        }
        return "<a href='" + getOneOffSubscriptionUrl(scene, wxMpService) + "'>" + content + "</a>";
    }

    /**
     * 获取发送一次性订阅链接
     * @param scene 创建值
     * @param wxMpService wxMpService
     * @return 一次性订阅链接
     */
    public static String getOneOffSubscriptionUrl(String scene, WxMpService wxMpService) {
        String appid = wxMpService.getWxMpConfigStorage().getAppId();
        String templateId = wxMpService.getWxMpConfigStorage().getTemplateId();
        String redirect_url = domain + "/wechat/sub/" + appid + "/test";
        StringBuilder builder = new StringBuilder();
        //组装一次性订阅链接
        builder.append(SEND_BASE_URL).append("&")
                .append("appid=").append(appid).append("&")
                .append("scene=").append(scene).append("&")
                .append("template_id=").append(templateId).append("&");
        try {
            builder.append("redirect_url=").append(URLEncoder.encode(redirect_url, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            builder.append("redirect_url=").append(URLEncoder.encode(redirect_url));
        }
        builder.append("#wechat_redirect");
        return builder.toString();
    }

    /**
     * 回复一次性订阅的模板信息给用户
     * @param oneOffSubscription 回复信息
     * @param wxMpService wxMpService
     * @throws WxErrorException 微信错误异常
     */
    public static void sendTemplateMessageToUser(OneOffSubscription oneOffSubscription, WxMpService wxMpService) throws WxErrorException {
            replyOneOffSubscribeRequest(oneOffSubscription, wxMpService);
    }

    /**
     * 建立与微信服务器的post链接，并发送模板消息给微信服务器
     * @param oneOffSubscription 回复信息
     * @param wxMpService wxMpService
     * @throws WxErrorException 微信错误异常
     */
    private static void replyOneOffSubscribeRequest(OneOffSubscription oneOffSubscription, WxMpService wxMpService) throws WxErrorException {
        //将回复信息转换成json
        String json = JsonUtils.wxToJson(oneOffSubscription);
        //设置mediaType
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
        //建立一个request请求
        Request request = new Request.Builder()
                .url(getReplyUrl(wxMpService))
                .post(requestBody)
                .build();
        //异步队列
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {   //请求失败
                log.info("send oneOffSubscription message failed oneOffSubscription:{} message:{}", json, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info("send oneOffSubscription message success oneOffSubscription:{} response:{}", json, response.body().string());
            }
        });
    }

    /**
     * 获取回复信息与微信建立连接所需的url
     * @param wxMpService wxMpService
     * @return 与微信建立连接所需的url
     * @throws WxErrorException 微信错误异常
     */
    private static String getReplyUrl(WxMpService wxMpService) throws WxErrorException {
        return REPLY_BASE_URL+ wxMpService.getAccessToken();
    }

    @Value("${domain}")
    public void setDomain(String target) {
        OneOffSubcriptionUtil.domain = target;
    }

}
