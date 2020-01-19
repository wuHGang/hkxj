package cn.hkxj.platform.controller;


import cn.hkxj.platform.elasticsearch.CourseTimeTableSearchService;
import cn.hkxj.platform.pojo.Exam;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.pojo.vo.CourseTimeTableVo;
import cn.hkxj.platform.service.CourseTimeTableService;
import cn.hkxj.platform.service.ExamTimeTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/timetable")
public class TimetableController {
    @Resource
    private CourseTimeTableService courseTimeTableService;
    @Resource
    private CourseTimeTableSearchService courseTimeTableSearchService;
    @Resource
    private ExamTimeTableService examTimeTableService;

    @GetMapping("/student")
    public WebResponse getTimeTableByStudent(@RequestParam(value = "account") String account) {
        if (Objects.isNull(account)) {
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
        }

        List<CourseTimeTableVo> courseTimeTableVoList = courseTimeTableService.getCourseTimeTableByStudent(Integer.parseInt(account));
        return WebResponse.success(courseTimeTableVoList);
    }

    @GetMapping("/teacher")
    public WebResponse getTimeTableByTeacher(@RequestParam(value = "account") String account) {
        if (Objects.isNull(account)) {
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
        }

        List<CourseTimeTableVo> courseTimeTableVoList = courseTimeTableService.getCourseTimeTableByTeacherAccount(account);
        return WebResponse.success(courseTimeTableVoList);
    }

    @GetMapping("/class")
    public WebResponse getTimeTableByClassNum(@RequestParam(value = "classNum") String classNum) {
        if (Objects.isNull(classNum)) {
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
        }

        List<CourseTimeTableVo> courseTimeTableVoList = courseTimeTableService.getCourseTimetableVoByClazz(classNum);
        return WebResponse.success(courseTimeTableVoList);
    }

    @GetMapping("/classroom")
    public WebResponse getTimeTableByClassroom(@RequestParam(value = "roomId") String roomId) {
        if (Objects.isNull(roomId)) {
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "教室号不能为空");
        }

        List<CourseTimeTableVo> courseTimeTableVoList = courseTimeTableService.getCourseTimeTableByClassroom(roomId);
        return WebResponse.success(courseTimeTableVoList);
    }

    @GetMapping("/course")
    public WebResponse getTimeTableByCourse(@RequestParam(value = "courseId") String courseId,
                                            @RequestParam(value = "courseOrder") String courseOrder) {

        List<CourseTimeTableVo> courseTimeTableVoList = courseTimeTableService.getCourseTimeTableByCourse(courseId, courseOrder);
        return WebResponse.success(courseTimeTableVoList);
    }

    @GetMapping("/search")
    public WebResponse searchTimeTable(@RequestParam(value = "q") String query,
                                       @RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "size", required = false) Integer size
    ) {

        if (page == null || size == null) {
            page = 0;
            size = 10;
        }

        return WebResponse.success(courseTimeTableSearchService.searchCourseTimeTableV2(page, size, query));
    }

    @GetMapping("/exam")
    public WebResponse getExamTimeTableByStudent(@RequestParam(value = "account") String account) {
        if (Objects.isNull(account)) {
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
        }

        List<Exam> examTimeList = examTimeTableService.getExamTimeList(Integer.parseInt(account));
        return WebResponse.success(examTimeList);
    }


}
