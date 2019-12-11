package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.pojo.constant.MiniProgram;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
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
        miniProgramService.getAccessToken();
    }
}