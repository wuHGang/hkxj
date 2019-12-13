package cn.hkxj.platform.service.wechat;


import cn.hkxj.platform.config.wechat.MiniProgramProperties;
import cn.hkxj.platform.dao.ScheduleTaskDao;
import cn.hkxj.platform.exceptions.UrpException;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.wechat.miniprogram.AccessTokenResponse;
import cn.hkxj.platform.pojo.wechat.miniprogram.AuthResponse;
import cn.hkxj.platform.pojo.wechat.miniprogram.Response;
import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeMessage;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MiniProgramService {
    @Resource
    private MiniProgramProperties miniProgramProperties;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ScheduleTaskDao scheduleTaskDao;

    private static final String root = "https://api.weixin.qq.com";
    private static final String authString = root + "/sns/jscode2session?appid=%s&secret=%s&js_code" +
            "=%s&grant_type=authorization_code";

    private static final String accessToken = root + "/cgi-bin/token?grant_type=client_credential&appid=%s&secret" +
            "=%s";

    private static final String SEND_SUBSCRIBE = root+ "/cgi-bin/message/subscribe/send?access_token=%s";

    public AuthResponse auth(String code) {


        String url = String.format(authString, miniProgramProperties.getAppId(), miniProgramProperties.getSecret(), code);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);

        return parseResponse(entity.getBody(), AuthResponse.class);

    }

    public void subscribe(String templateId, String openid){
        SubscribeScene scene = SubscribeScene.getByMiniProgramTemplateId(templateId);
        List<ScheduleTask> taskList = scheduleTaskDao.selectByPojo(new ScheduleTask()
                .setAppid(miniProgramProperties.getAppId())
                .setOpenid(openid)
                .setScene(Integer.parseInt(scene.getScene()))
        );
        if(taskList.size() == 0){
            scheduleTaskDao.insertSelective(new ScheduleTask()
                    .setIsSubscribe((byte) 1)
                    .setAppid(miniProgramProperties.getAppId())
                    .setOpenid(openid)
                    .setScene(Integer.parseInt(scene.getScene()))
                    .setSendStatus((byte) 0)
                    .setTaskCount(0)
            );
        }




    }

    @Retryable(value = Exception.class, maxAttempts = 3)
    public String getAccessToken() {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        if(isAccessTokenExpire()){
            String url = String.format(accessToken, miniProgramProperties.getAppId(), miniProgramProperties.getSecret());
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
            AccessTokenResponse response = parseResponse(entity.getBody(), AccessTokenResponse.class);
            if(response.getErrcode() == 0){
                opsForValue.set(RedisKeys.MINI_PROGRAM_ACCESS_TOKEN.getName(), response.getAccessToken(),
                        response.getExpiresIn(), TimeUnit.SECONDS);
                return response.getAccessToken();
            }else {
                throw new RuntimeException();
            }

        }else {
            return opsForValue.get(RedisKeys.MINI_PROGRAM_ACCESS_TOKEN.getName());
        }

    }



    public void sendSubscribeMessage(SubscribeMessage message) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);


        String url = String.format(SEND_SUBSCRIBE, getAccessToken());

        HttpEntity<String> request = new HttpEntity<>(JSON.toJSONString(message), headers);
        log.info("send mini app subscribe message data {}", message);
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request , String.class );
            log.info("send mini app subscribe message success response {}", response.getBody());
        }catch (Exception e){
            log.error("send mini app subscribe message error", e);
        }


    }

    boolean isAccessTokenExpire(){

        return !BooleanUtils.toBoolean(stringRedisTemplate.hasKey(RedisKeys.MINI_PROGRAM_ACCESS_TOKEN.getName()));
    }

    <T extends Response> T parseResponse(String result, Class<T> clazz){
        Gson gson = new Gson();
        T t = gson.fromJson(result, clazz);
        if (t == null) {
            throw new RuntimeException("parse object error " + clazz.toString());
        }
        if (t.getErrcode() != 0) {
            log.error("request fail code:{} msg:{}  body:{}", t.getErrcode(), t.getErrMsg(), t);
        }

        return t;
    }

}
