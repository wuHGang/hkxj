package cn.hkxj.platform.service.wechat;


import cn.hkxj.platform.config.wechat.MiniProgramProperties;
import cn.hkxj.platform.pojo.wechat.miniprogram.AccessTokenResponse;
import cn.hkxj.platform.pojo.wechat.miniprogram.AuthResponse;
import cn.hkxj.platform.pojo.wechat.miniprogram.Response;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@Service
public class MiniProgramService {
    @Resource
    MiniProgramProperties miniProgramProperties;

    private final String root = "https://api.weixin.qq.com";
    private final String authString = root + "/sns/jscode2session?appid=%s&secret=%s&js_code" +
            "=%s&grant_type=authorization_code";

    private final String accessToken = root + "/cgi-bin/token?grant_type=client_credential&appid=%s&secret" +
            "=%s";

    public AuthResponse auth(String code) {


        String url = String.format(authString, miniProgramProperties.getAppId(), miniProgramProperties.getSecret(), code);

        return getForEntity(url, AuthResponse.class);

    }

    public AuthResponse login(String code, String account) {
        AuthResponse response = auth(code);

        return response;
    }

    public void getAccessToken() {

        String url = String.format(accessToken, miniProgramProperties.getAppId(), miniProgramProperties.getSecret());
        AccessTokenResponse response = getForEntity(url, AccessTokenResponse.class);

    }


    /**
     * 这个由于微信返回的content type为 text  无法反序列化所以加一层包装
     */
    public <T extends Response> T getForEntity(String url, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);

        //这里fastJson 无法解析父类得属性
//        T t = JSON.parseObject(entity.getBody(), clazz);
        Gson gson = new Gson();
        T t = gson.fromJson(entity.getBody(), clazz);
        if (t == null) {
            throw new RuntimeException("parse object error " + clazz.toString());
        }
        if (t.getErrcode() != 0) {
            log.error("request fail code:{} msg:{} url:{} body:{}", t.getErrcode(), t.getErrMsg(), url, t);
        }

        return t;
    }


}
