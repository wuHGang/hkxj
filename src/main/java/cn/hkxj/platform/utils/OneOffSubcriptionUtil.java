package cn.hkxj.platform.utils;

import cn.hkxj.platform.pojo.wechat.OneOffSubscription;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    private static final String BASE_URL = "https://mp.weixin.qq.com/mp/subscribemsg?action=get_confirm";
    private static final String REPLY_URL = "https://api.weixin.qq.com/cgi-bin/message/template/subscribe?access_token=";

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

    public static String getOneOffSubscriptionUrl(String scene, WxMpService wxMpService) {
        String appid = wxMpService.getWxMpConfigStorage().getAppId();
        String templateId = wxMpService.getWxMpConfigStorage().getTemplateId();
        String redirect_url = domain + "/wechat/sub/" + appid + "/test";
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_URL).append("&")
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

    public static void sendTemplateMessageToUser(OneOffSubscription oneOffSubscription, WxMpService wxMpService) throws WxErrorException {
            replyOneOffSubscribeRequest(oneOffSubscription, wxMpService);
    }

    private static void replyOneOffSubscribeRequest(OneOffSubscription oneOffSubscription, WxMpService wxMpService) throws WxErrorException{

        String json = JsonUtils.wxToJson(oneOffSubscription);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json;charset=UTF-8"), json);

        Request request = new Request.Builder()
                .url(getReplyUrl(wxMpService))
                .post(requestBody)
                .build();
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

    private static String getReplyUrl(WxMpService wxMpService) throws WxErrorException {
        return REPLY_URL + wxMpService.getAccessToken();
    }

    @Value("${domain}")
    public void setDomain(String target) {
        OneOffSubcriptionUtil.domain = target;
    }

}
