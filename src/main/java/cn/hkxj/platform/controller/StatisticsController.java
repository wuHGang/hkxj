
package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.request.StatisticsRequest;
import cn.hkxj.platform.pojo.vo.StatisticsV0;
import cn.hkxj.platform.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhouqinglai
 * @version version
 * @title StatisticsController
 * @desc
 * @date: 2019年05月03日
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    private StatisticsService statisticsService;

    /**
     * 接口访问统计
     * @param request
     * @return
     */
    @GetMapping("/interface")
    public WebResponse<StatisticsV0> listStatisticsByPage(StatisticsRequest request) {
        request.validateParam();
        return WebResponse.success(statisticsService.listInterfaceStatisticsByPage(request.getCurrentPage(),request.getSize()));
    }

}
