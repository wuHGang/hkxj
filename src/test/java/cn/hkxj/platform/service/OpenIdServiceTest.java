package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class OpenIdServiceTest {
    @Resource
    private OpenIdService openIdService;

    @Test
    public void unBind(){
        openIdService.openIdUnbind("o6393wqXpaxROMjiy8RAgPLqWFF8", "wx2212ea680ca5c05d");
    }

}