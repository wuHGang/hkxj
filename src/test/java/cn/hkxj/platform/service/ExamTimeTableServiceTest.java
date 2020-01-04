package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.MiniProgramOpenIdDao;
import cn.hkxj.platform.pojo.Exam;
import cn.hkxj.platform.pojo.MiniProgramOpenid;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class ExamTimeTableServiceTest {
    @Resource
    private ExamTimeTableService examTimeTableService;
    @Resource
    private MiniProgramOpenIdDao miniProgramOpenIdDao;


    @Test
    public void getExamtimeList() {

        for (Exam exam : examTimeTableService.getExamTimeList(2019020630)) {
            System.out.println(exam);
        }
    }


    @Test
    public void updateAllExam() {
        List<MiniProgramOpenid> openidList = miniProgramOpenIdDao.selectByPojo(new MiniProgramOpenid());
        for (MiniProgramOpenid openid : openidList) {
            log.info("account {} start", openid.getAccount());
            try {
                examTimeTableService.getExamTimeList(openid.getAccount());
            }catch (Exception e){
                log.error("error", e);
            }finally {
                log.info("account {} finish", openid.getAccount());
            }

        }


    }
}