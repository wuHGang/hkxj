package cn.hkxj.platform.task;

import cn.hkxj.platform.service.CourseSubscribeService;
import cn.hkxj.platform.service.EmptyRoomService;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * @author Syaeldon
 */
public class EmptyRoomTask {
    @Resource(name = "emptyRoomService")
    private EmptyRoomService emptyRoomService;

    /**
     * 自动爬取三天内的所有空教室数据作为热点数据
     */
    @Async
    @Scheduled(cron = "0 0 1 * * ?")
    void autoGetEmptyRoom() {
        int day = SchoolTimeUtil.getDayOfWeek();
        for (int i = 0; i < 3; i++) {
            emptyRoomService.getFullEmptyRoomReply(String.valueOf(SchoolTimeUtil.getSchoolWeek()), "01", day + i, 0);
            emptyRoomService.getFullEmptyRoomReply(String.valueOf(SchoolTimeUtil.getSchoolWeek()), "02", day + i, 0);
            emptyRoomService.getFullEmptyRoomReply(String.valueOf(SchoolTimeUtil.getSchoolWeek()), "03", day + i, 0);
        }
    }
}
