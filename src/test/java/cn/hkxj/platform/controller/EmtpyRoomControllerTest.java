package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.EmptyRoom;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.service.EmptyRoomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EmtpyRoomControllerTest {
    @Resource(name = "emptyRoomService")
    private EmptyRoomService emptyRoomService;


    @Test
    public void getEmptyRoom() {

        int schoolWeek = 11;
        int dayOfWeek = 4;
        int order = 0;
        String buildingName = "01";
        int floor = 2;
        try {

            List<EmptyRoom> list = emptyRoomService.getFullEmptyRoomReply(String.valueOf(schoolWeek), buildingName, dayOfWeek, floor);
            for (EmptyRoom emptyRoom:list){
                System.out.println(emptyRoom.getName());
                System.out.println(emptyRoom.getOrderList());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}