package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.service.EmptyRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author xie
 * @data 2019/6/1
 * 提供空教室查询功能API
 */
@RestController
@Slf4j
public class EmptyRoomController {

    @Resource(name = "emptyRoomService")
    private EmptyRoomService emptyRoomService;

    @RequestMapping(value = "/emptyRoom", method = RequestMethod.POST)
    public WebResponse getEmptyRoom(@RequestParam("schoolWeek") int schoolWeek,
                                    @RequestParam("dayOfWeek") int dayOfWeek,
                                    @RequestParam("order") int order,
                                    @RequestParam("building") String buildingName,
                                    @RequestParam("floor") int floor) {

        try {
            return WebResponse.success(emptyRoomService.getEmptyRoomReply(String.valueOf(schoolWeek), buildingName, dayOfWeek, order, floor));
        } catch (Exception e) {
            log.error("fail to get emptyRoom data {},{},{},{},{}", schoolWeek, dayOfWeek, order, buildingName, floor, e);
        }
        return WebResponse.fail(ErrorCode.NO_DATA.getErrorCode(), "fail to get emptyRoom Data");
    }

    /**
     * 对order为0的请求进行不分节次的查询
     * @param schoolWeek
     * @param dayOfWeek
     * @param order
     * @param buildingName
     * @param floor
     * @return
     */
    @RequestMapping(value = "/emptyRoomV2", method = RequestMethod.POST)
    public WebResponse getEmptyRoomV2(@RequestParam("schoolWeek") int schoolWeek,
                                      @RequestParam("building") String buildingName,
                                      @RequestParam("dayOfWeek") int dayOfWeek,
                                      @RequestParam(value = "order", required = false) Integer order,
                                      @RequestParam(value = "floor", required = false) Integer floor) {

        if(order == null){
            order = 0;
        }

        if(floor == null){
            floor = 0;
        }

        if (order != 0) {
            try {
                return WebResponse.success(emptyRoomService.getEmptyRoomReply(String.valueOf(schoolWeek), buildingName, dayOfWeek, order, floor));
            } catch (Exception e) {
                log.error("fail to get emptyRoom data {},{},{},{},{}", schoolWeek, dayOfWeek, order, buildingName, floor, e);
            }
        } else {
            try {
                return WebResponse.success(emptyRoomService.getFullEmptyRoomReply(String.valueOf(schoolWeek), buildingName, dayOfWeek, floor));
            } catch (Exception e) {
                log.error("fail to get emptyRoom data {},{},{},{},{}", schoolWeek, dayOfWeek, order, buildingName, floor, e);
            }
        }

        return WebResponse.fail(ErrorCode.NO_DATA.getErrorCode(), "fail to get emptyRoom Data");
    }





}
