package cn.hkxj.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserBindingControllerTest {

    @Resource
    private UserBindingController userBindingController;

    @Test
    public void autoEvaluate() {

        userBindingController.autoEvaluate("2017024716", "1" ,"wx541fd36e6b400648", "oCxRO1HEEhx2m8hn4-ujl8nE1ZX0");
    }
}