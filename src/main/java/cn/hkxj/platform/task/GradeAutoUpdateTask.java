package cn.hkxj.platform.task;

import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.mapper.SubscribeGradeUpdateMapper;
import cn.hkxj.platform.mapper.TaskMapper;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.pojo.wechat.SubscribeGradeUpdate;
import cn.hkxj.platform.service.GradeSearchService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

@Slf4j
@Service
public class GradeAutoUpdateTask {
    /**
     * 存放学生查询记录的队列
     */
    private static Queue<Student> ACCOUNT_QUEUE = new LinkedList<>();
    /**
     * 用于过滤没有被消费的学号
     */
    private static HashSet<Student> ACCOUNT_SET = new HashSet<>();

    private HashMap<Integer,String> openidMap=new HashMap<>();
    @Resource
    private GradeSearchService gradeSearchService;
    @Resource
    private SubscribeGradeUpdateMapper subscribeGradeUpdateMapper;
    @Resource
    private OpenidMapper openidMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private WxMpService wxMpService;
    @Resource
    private TaskMapper taskMapper;
    @Value("scheduled.gradeUpdate")
    private String updateSwitch;

    public static void addStudentToQueue(Student student) {
        if (ACCOUNT_SET.contains(student)) {
            return;
        }
        ACCOUNT_SET.add(student);
        ACCOUNT_QUEUE.offer(student);
    }

    @Scheduled(cron = "0 0/20 * * * ?")
    private void autoUpdateGrade() {
        if(!BooleanUtils.toBoolean(updateSwitch)){
            return;
        }
        getStudentQueue();
        Student student = ACCOUNT_QUEUE.poll();
        while (Objects.nonNull(student)) {
            try {
                taskMapper.taskCountUpdate(openidMap.get(student.getAccount()),1);
                List<GradeAndCourse> gradeFromSpider = gradeSearchService.getCurrentGradeFromSpider(student);
                List<GradeAndCourse> studentGrades=gradeSearchService.saveGradeAndCourse(student, gradeFromSpider);
                if (!CollectionUtils.isEmpty(studentGrades)) {
                    String result = gradeSearchService.gradeListToText(studentGrades);
                    WxMpKefuMessage kefuMessage = getKefuMessage(student, result);
                    wxMpService.getKefuService().sendKefuMessage(kefuMessage);
                }

            } catch (WxErrorException e) {
                log.warn("account {} send grade update message error {}", e.getMessage());
            } catch (Exception e) {
                log.error("grade update task error", e);
            }
            ACCOUNT_SET.remove(student);
            student = ACCOUNT_QUEUE.poll();
        }
    }

    /**
     * 从订阅成绩更新的表中获取更新队列
     */
    private void getStudentQueue(){
        List<SubscribeGradeUpdate> list=subscribeGradeUpdateMapper.getSubscribedList();
        List<String> openidStringList=new ArrayList<>();
        for(SubscribeGradeUpdate o:list){
            openidStringList.add(o.getOpenid());
        }
        List<Openid> openidList=openidMapper.getOpenIdsByOpenIds(openidStringList);
        openidMap.clear();
        for(Openid openid:openidList){
            openidMap.put(openid.getAccount(),openid.getOpenid());
            addStudentToQueue(studentMapper.selectByAccount(openid.getAccount()));
        }
    }

    /**
     * 获取回复文本
     * @param student
     * @param content
     * @return
     */
    private WxMpKefuMessage getKefuMessage(Student student, String content) {

        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent("成绩更新\n"+content );
        wxMpKefuMessage.setToUser(openidMap.get(student.getAccount()));
        wxMpKefuMessage.setMsgType("text");

        log.info("student account {} grade update {}", student.getAccount(), content);

        return wxMpKefuMessage;
    }


}
