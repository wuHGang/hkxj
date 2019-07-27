package cn.hkxj.platform.task;

import cn.hkxj.platform.service.NewUrpSpiderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SessionFlashTask {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;

    @Scheduled(cron = "0 */20 0 * * ?")
    public void updateSession(){

    }
}
