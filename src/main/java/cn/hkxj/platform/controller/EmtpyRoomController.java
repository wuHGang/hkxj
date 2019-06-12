package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.EmptyRoom;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.constant.LessonOrder;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.timetable.RoomTimeTable;
import cn.hkxj.platform.service.EmptyRoomService;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalTime;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;


/**
 * @author xie
 * @data 2019/6/1
 * 提供空教室查询功能API
 */
@RestController
@Slf4j
public class EmtpyRoomController {

    @Resource(name="emptyRoomService")
    private EmptyRoomService emptyRoomService;

    @RequestMapping(value = "/emptyRoom", method = RequestMethod.POST)
    public WebResponse getEmptyRoom(@RequestParam("schoolWeek")int schoolWeek,
                                    @RequestParam("dayOfWeek")int dayOfWeek,
                                    @RequestParam("order")int order,
                                    @RequestParam("building")String buildingName,
                                    @RequestParam("floor")int floor){
        log.info("emptyRoom search{},{},{},{},{}", schoolWeek, dayOfWeek,order,buildingName,floor);
            try {
                return WebResponse.success(emptyRoomService.getEmptyRoomReply(emptyRoomService.getRoomTimeTableByTime(schoolWeek,dayOfWeek,order,Building.getBuildingByName(buildingName),floor)));
            }catch (IOException e){
                log.info("fail to Serialization emptyRoom {},{},{},{},{}", schoolWeek, dayOfWeek,order,buildingName,floor);
            }
            return WebResponse.fail(500,"fail to get emptyRoom Data");
        }


}
