package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.timetable.RoomTimeTable;
import cn.hkxj.platform.pojo.vo.RoomOrderTimeTableVO;
import cn.hkxj.platform.pojo.vo.RoomTimeTableVO;
import cn.hkxj.platform.service.EmptyRoomService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author xie
 * @data 2019/6/1
 * 提供空教室查询功能API
 */
@RestController
@Slf4j
public class EmtpyRoomController {

    @Resource(name = "emptyRoomService")
    private EmptyRoomService emptyRoomService;

    @RequestMapping(value = "/emptyRoom", method = RequestMethod.POST)
    public WebResponse getEmptyRoom(@RequestParam("schoolWeek") int schoolWeek,
                                    @RequestParam("dayOfWeek") int dayOfWeek,
                                    @RequestParam("order") int order,
                                    @RequestParam("building") String buildingName,
                                    @RequestParam("floor") int floor) {
        log.info("emptyRoom search{},{},{},{},{}", schoolWeek, dayOfWeek, order, buildingName, floor);
        try {
            return WebResponse.success(emptyRoomService.getEmptyRoomReply(emptyRoomService.getRoomTimeTableByTime(schoolWeek, dayOfWeek, order, Building.getBuildingByName(buildingName), floor)));
        } catch (IOException e) {
            log.error("fail to get emptyRoom data {},{},{},{},{}", schoolWeek, dayOfWeek, order, buildingName, floor, e);
        }
        return WebResponse.fail(ErrorCode.NO_DATA.getErrorCode(), "fail to get emptyRoom Data");
    }

    @RequestMapping(value = "/emptyRoomV2", method = RequestMethod.POST)
    public WebResponse getEmptyRoomV2(@RequestParam("schoolWeek") int schoolWeek,
                                      @RequestParam("dayOfWeek") int dayOfWeek,
                                      @RequestParam("order") int order,
                                      @RequestParam("building") String buildingName,
                                      @RequestParam("floor") int floor) {
        log.info("emptyRoom search{},{},{},{},{}", schoolWeek, dayOfWeek, order, buildingName, floor);
        return WebResponse.success();
//        try {
//            List<RoomTimeTable> table = emptyRoomService.getRoomTimeTableByTime(schoolWeek, dayOfWeek, order, Building.getBuildingByName(buildingName), floor);
//            return WebResponse.success(adapteResult(table));
//        } catch (IOException e) {
//            log.error("fail to get emptyRoom data {},{},{},{},{}", schoolWeek, dayOfWeek, order, buildingName, floor, e);
//        }
//        return WebResponse.fail(ErrorCode.NO_DATA.getErrorCode(), "fail to get emptyRoom Data");
    }


    private List<RoomTimeTableVO> adapteResult(List<RoomTimeTable> roomTimeTableList) {
        List<Integer> order = Lists.newArrayList(1, 3, 5, 7, 9);
        List<RoomTimeTableVO> roomTimeTableVOS = new ArrayList<>();
        for (RoomTimeTable roomTimeTable : roomTimeTableList) {
            RoomTimeTableVO roomTimeTableVO = new RoomTimeTableVO();
            roomTimeTableVO.setRoom(roomTimeTable.getRoom());
            List<CourseTimeTable> courseTimeTable1 = roomTimeTable.getCourseTimeTable();
            Map<Integer, CourseTimeTable> courseTimeTableMap = courseTimeTable1.stream()
                    .collect(Collectors.toMap(CourseTimeTable::getOrder, Function.identity(), (v1, v2) -> v1));

            List<RoomOrderTimeTableVO> orderVOList = new ArrayList<>();
            roomTimeTableVO.setRoomOrderTimeTableList(orderVOList);

            for (Integer integer : order) {
                RoomOrderTimeTableVO tableVO = new RoomOrderTimeTableVO();
                tableVO.setOrder(integer);
                if (courseTimeTableMap.containsKey(integer)) {
                    tableVO.setEmpty(false);
                    tableVO.setCourseTimeTable(courseTimeTableMap.get(integer));
                }
                orderVOList.add(tableVO);
            }
            roomTimeTableVOS.add(roomTimeTableVO);
        }
        return roomTimeTableVOS;
    }


}
