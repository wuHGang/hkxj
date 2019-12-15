package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.CourseDao;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.dao.UrpClassRoomDao;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
@Slf4j
@Service
public class ExamTimeTableService {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private StudentDao studentDao;
    @Resource
    private UrpCourseService urpCourseService;
    @Resource
    private UrpClassRoomDao urpClassRoomDao;


    public List<Exam> getExamtimeList(int account) {
        Student student = studentDao.selectStudentByAccount(account);

        return newUrpSpiderService.getExamTime(student).stream()
                .filter(x-> StringUtils.isNotEmpty(x.getDate()))
                .map(x -> {
                            if(StringUtils.isEmpty(x.getExamTime())){
                                return new Exam()
                                        .setCourse(getCourseFromExamText(x.getCourseName()))
                                        .setDate(new Date())
                                        .setExamName(x.getExamName())
                                        .setClassRoom(new UrpClassroom())
                                        ;

                            }

                            String[] timeSplit = x.getExamTime().split("-");
                            return new Exam()
                                    .setCourse(getCourseFromExamText(x.getCourseName()))
                                    .setDate(DateUtils.localDateToDate(x.getDate(), DateUtils.DEFAULT_PATTERN))
                                    .setExamName(x.getExamName())
                                    .setStartTime(timeSplit[0])
                                    .setEndTime(timeSplit[1])
                                    .setClassRoom(getClassRoomFromText(x.getLocation()))
                                    .setExamDay(x.getWeek())
                                    .setExamWeekOfTerm(x.getWeekOfTerm());
                        }

                )
                .collect(Collectors.toList());
    }

    private Course getCourseFromExamText(String examText) {

        String pattern = "（(.*?)-(.*?)）(.*?)";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(examText);

        if (m.find()) {
            String num = m.group(1);
            String order = m.group(2);
            String courseName = m.group(3);
            Course course = urpCourseService.getCurrentTermCourse(num, order, new Course().setCourseOrder(order));
            if (course == null) {
                log.error("can not find course {} {}", num, order);
            }

            return course;

        } else {
            log.error("can not parse exam text {}", examText);
            return new Course();
        }

    }

    private UrpClassroom getClassRoomFromText(String date) {
        String[] split = date.split(" ");

        if(urpClassRoomDao.selectByName(split[3]) != null){
            return urpClassRoomDao.selectByName(split[3]);
        }
        return new UrpClassroom();
    }

    public static void main(String[] args) {
        String date = "地点: 主校区 主校区科大 科S212";
        String[] split = date.split(" ");
        System.out.println(split[3]);
    }


}
