package cn.hkxj.platform.controller;

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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author xie
 * @data 2019/6/1
 * 提供空教室查询功能API
 */
@RestController
//@RequestMapping("/emptyRoom")
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

            Building building=Building.getBuildingByName(buildingName);
            List<RoomTimeTable> roomTimeTable = emptyRoomService.getRoomTimeTableByTime(schoolWeek,dayOfWeek,order,building,floor);

            return WebResponse.success(getReply(roomTimeTable));
        }

    private List<Map> getReply(List<RoomTimeTable> roomTimeTableList){
        List<Map> replayList=new ArrayList<>();
        for (RoomTimeTable table : roomTimeTableList) {
            replayList.add(tableToMap(table));
        }
        return replayList;
    }

    private Map tableToMap(RoomTimeTable roomTimeTable) {
        Map map = new HashMap();
        List<Integer> orderList = new LinkedList();
        Room room = roomTimeTable.getRoom();
        List<CourseTimeTable> courseTimeTable = roomTimeTable.getCourseTimeTable();
        if (Objects.isNull(courseTimeTable) || courseTimeTable.size() == 0){
            orderList.add(-1);
        }
       else {
            for (CourseTimeTable table : courseTimeTable) {
                orderList.add(table.getOrder());
            }
        }
        map.put("roomOrder", orderList);
        map.put("roomName", room.getName());

        return map;
    }
}
