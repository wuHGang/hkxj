package cn.hkxj.platform.task;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.AllGradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.AppSpiderService;
import cn.hkxj.platform.service.GradeSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.LinkedList;
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
    @Resource
    private GradeSearchService gradeSearchService;
    @Resource
    private AppSpiderService appSpiderService;

    public static void addStudentToQueue(Student student) {
        if (ACCOUNT_SET.contains(student)) {
            return;
        }
        ACCOUNT_SET.add(student);
        ACCOUNT_QUEUE.offer(student);
    }

    @Scheduled(cron = "0 0 9 ? * MON-FRI")
    private void autoUpdateGrade() {

        Student student = ACCOUNT_QUEUE.poll();
        while (Objects.nonNull(student)) {
            try {
                AllGradeAndCourse gradeAndCourset = appSpiderService.getGradeAndCourseByAccount(student.getAccount());
                gradeSearchService.saveGradeAndCourse(student, gradeAndCourset.getCurrentTermGrade());
            } catch (PasswordUncorrectException e) {
                log.error("account{} app spider password error", student.getAccount());
            } catch (Exception e) {
                log.error("grade update task error", e);
            }
            ACCOUNT_SET.remove(student);
            student = ACCOUNT_QUEUE.poll();
        }
    }
}
