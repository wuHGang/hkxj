package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.service.EmptyRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;


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
                log.error("fail to get emptyRoom data {},{},{},{},{}", schoolWeek, dayOfWeek,order,buildingName,floor);
            }
            return WebResponse.fail(ErrorCode.NO_DATA.getErrorCode(),"fail to get emptyRoom Data");
        }


}
