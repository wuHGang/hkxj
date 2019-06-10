package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.service.EmptyRoomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EmtpyRoomControllerTest {
    @Resource(name="emptyRoomService")
    private EmptyRoomService emptyRoomService;

    @Test
    public void getEmptyRoom() {

        int schoolWeek=13;int dayOfWeek=2;int order=4;String buildingName="主楼";int floor=1;
        try {

                System.out.println(emptyRoomService.getEmptyRoomReply(emptyRoomService.getRoomTimeTableByTime(schoolWeek,dayOfWeek,order,Building.getBuildingByName(buildingName),floor)));



        }catch (Exception e){
            System.out.println("!!");
        }
    }
}