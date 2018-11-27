package cn.hkxj.platform.task;

import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.GradeSearchService;
import org.springframework.scheduling.annotation.Scheduled;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GradeAutoUpadeTask {
    private GradeSearchService gradeSearchService;
    @Resource
    private OpenidMapper openidMapper;
    @Resource
    private SubscribeOpenidMapper subscribeOpenidMapper;
    @Resource
    private StudentMapper studentMapper;

    @Scheduled(cron = "0 0 9 ? * MON-FRI")
    private void autoUpdateGrade()throws IOException {
        List<String> openIds = subscribeOpenidMapper.getAllSubscribeOpenids();
        List<Openid> allOpenIds = openidMapper.getOpenIdsByOpenIds(openIds);
        for(Openid openid:allOpenIds){
            Student student=studentMapper.selectByAccount(openid.getAccount());
            if(student!=null) {
                gradeSearchService.saveGradeAndCourse( student.getAccount(),
                                                    student.getPassword(),
                                                    gradeSearchService.getGradeList(student.getAccount()));
            }
            else {
                log.error("student information doesn't exist");
            }
        }
    }
}
