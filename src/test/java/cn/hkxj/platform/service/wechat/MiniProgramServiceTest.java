package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeGradeData;
import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeMessage;
import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeValue;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MiniProgramServiceTest {
    @Resource
    private MiniProgramService miniProgramService;

    @Test
    public void auth() {
        miniProgramService.auth("043907Az1RSB5904nKBz1A8Yzz1907AY");
    }

    @Test
    public void getAccessToken() {
        String accessToken = miniProgramService.getAccessToken();
        log.info(accessToken);
    }

    @Test
    public void sendSubscribeMessage() {
        SubscribeMessage message = new SubscribeMessage();
         message.setToUser("oOzb90OTAmqThH_sXTdhWoxXiCAg")
                .setTemplateId("dmE0nyulM8OVcUs-KojDxCYECrKTmzOGDkEUUm2T5UE")
                .setData(new SubscribeGradeData()
                        .setCourseName(new SubscribeValue("低等数学"))
                        .setName(new SubscribeValue("吴彦祖"))
                        .setScore(new SubscribeValue("59.99"))
                        .setRemark(new SubscribeValue("挂科了？原来是自己编的，哦！那没事了"))
                );

        String s = JSON.toJSONString(message);
        miniProgramService.sendSubscribeMessage(message);
    }


    @Test
    public void isAccessTokenExpire(){
        miniProgramService.isAccessTokenExpire();
    }
}