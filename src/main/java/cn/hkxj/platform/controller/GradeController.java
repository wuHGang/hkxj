package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.GradeSearchResult;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.vo.GradeResultVo;
import cn.hkxj.platform.pojo.vo.GradeVo;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.spider.newmodel.grade.detail.GradeDetailSearchPost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junrong.chen
 */
@Slf4j
@RestController
public class GradeController {
    @Resource
    private NewGradeSearchService newGradeSearchService;


    @RequestMapping(value = "/nowGradeV2", method = RequestMethod.POST)
    public WebResponse getNowGradeV2(@RequestParam("account") String account,
                                     @RequestParam("password") String password) {
        List<GradeVo> result = newGradeSearchService.getCurrentTermGrade(account, password);
        return WebResponse.success(result);
    }

    @RequestMapping(value = "/grade", method = RequestMethod.POST)
    public WebResponse getGrade(@RequestParam("account") String account,
                                     @RequestParam("password") String password) {
        GradeResultVo result = newGradeSearchService.getGrade(account, password);
        return WebResponse.success(result);
    }

    @RequestMapping(value = "/grade/detail", method = RequestMethod.POST)
    public WebResponse gradeDetail(@RequestParam("account") String account,
                                   @RequestParam("password") String password,
                                   @RequestParam("planNumber") String planNumber,
                                   @RequestParam("courseNumber") String courseNumber,
                                   @RequestParam("examTime") String examTime,
                                   @RequestParam("courseSequenceNumber") String courseSequenceNumber
                                   ) {

        GradeDetailSearchPost searchPost = new GradeDetailSearchPost()
                .setExecutiveEducationPlanNumber(planNumber)
                .setCourseNumber(courseNumber)
                .setCourseSequenceNumber(courseSequenceNumber)
                .setExamTime(examTime);

        return WebResponse.success(newGradeSearchService.getGradeDetail(account, searchPost));
    }
}
