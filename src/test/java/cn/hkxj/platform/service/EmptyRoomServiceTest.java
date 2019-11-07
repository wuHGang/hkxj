package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.EmptyRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2019/6/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class EmptyRoomServiceTest {
    @Resource
    private EmptyRoomService emptyRoomService;

    @Test
    public void getEmptyRoomReply() throws IOException {
        List<EmptyRoom> emptyRoomReply = emptyRoomService.getEmptyRoomReply("8", "02", 4,3,0);
        for (EmptyRoom s:emptyRoomReply){
            System.out.println(s.getName());
        }
    }
}