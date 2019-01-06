package cn.hkxj.platform.task;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.mapper.SubscribeGradeUpdateMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.service.AppSpiderService;
import cn.hkxj.platform.service.GradeSearchService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

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

    private HashMap<Integer,String> openidMap;
    @Resource
    private GradeSearchService gradeSearchService;
    @Resource
    private AppSpiderService appSpiderService;
    @Resource
    private SubscribeGradeUpdateMapper subscribeGradeUpdateMapper;
    @Resource
    private OpenidMapper openidMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    WxMpService wxMpService;

    public static void addStudentToQueue(Student student) {
        if (ACCOUNT_SET.contains(student)) {
            return;
        }
        ACCOUNT_SET.add(student);
        ACCOUNT_QUEUE.offer(student);
    }

    @Scheduled(cron = "0 0 9 * * ?")
    private void autoUpdateGrade() {
        getStudentQueue();
        Student student = ACCOUNT_QUEUE.poll();
        while (Objects.nonNull(student)) {
            try {
                AllGradeAndCourse gradeAndCourse = appSpiderService.getGradeAndCourseByAccount(student.getAccount());
                List<GradeAndCourse> studentGrades=gradeSearchService.saveGradeAndCourse(student, gradeAndCourse.getCurrentTermGrade());
                if(studentGrades!=null){
                    wxMpService.getKefuService().sendKefuMessage(getKefuMessage(openidMap.get(student.getAccount()), gradeListToText(studentGrades)));
                }
            } catch (PasswordUncorrectException e) {
                log.error("account{} app spider password error", student.getAccount());
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
        openidMap=new HashMap<>();
        for(Openid openid:openidList){
            openidMap.put(openid.getAccount(),openid.getOpenid());
            addStudentToQueue(studentMapper.selectByAccount(openid.getAccount()));
        }
    }

    /**
     * 获取回复文本
     * @param openid
     * @param content
     * @return
     */
    private WxMpKefuMessage getKefuMessage(String openid,String content) {
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent("成绩更新\n"+content );
        wxMpKefuMessage.setToUser(openid);
        wxMpKefuMessage.setMsgType("text");
        return wxMpKefuMessage;
    }

    /**
     * 将学生成绩文本化
     *
     * @param studentGrades 学生全部成绩
     */
    public String gradeListToText(List<GradeAndCourse> studentGrades) {
        StringBuffer buffer = new StringBuffer();
        boolean i = true;
        if (studentGrades.size() == 0) {
            buffer.append("尚无本学期成绩");
        } else {
            AllGradeAndCourse allGradeAndCourse = new AllGradeAndCourse();
            allGradeAndCourse.addGradeAndCourse(studentGrades);
            for (GradeAndCourse gradeAndCourse : allGradeAndCourse.getCurrentTermGrade()) {
                if (i) {
                    i = false;
                    buffer.append("- - - - - - - - - - - - - -\n");
                    buffer.append("|").append(gradeAndCourse.getGrade().getYear()).append("学年，第").append(gradeAndCourse.getGrade().getTerm()).append("学期|\n");
                    buffer.append("- - - - - - - - - - - - - -\n\n");
                }
                int grade = gradeAndCourse.getGrade().getScore();
                buffer.append("考试名称：").append(gradeAndCourse.getCourse().getName()).append("\n")
                        .append("成绩：").append(grade == -1 ? "" : grade / 10).append("   学分：")
                        .append(gradeAndCourse.getGrade().getPoint() / 10).append("\n\n");
            }
        }
        return buffer.toString();
    }

}
