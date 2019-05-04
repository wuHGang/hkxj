
package cn.hkxj.platform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hkxj.platform.pojo.constant.StatisticsTypeEnum;
import cn.hkxj.platform.pojo.vo.PagerVO;
import cn.hkxj.platform.pojo.vo.StatisticsDetailVo;
import cn.hkxj.platform.pojo.vo.StatisticsV0;

/**
 * @author zhouqinglai
 * @version version
 * @title StatisticsService
 * @desc 统计服务
 * @date: 2019年05月02日
 */
@Service
public class StatisticsService {

    @Resource
    private CacheService cacheService;
    /**
     * 分页获取接口统计数据
     * @param  currentPage
     * @param size
     */
    public StatisticsV0 listInterfaceStatisticsByPage(Integer currentPage,Integer size) {
        final Long count = cacheService.sizeByBizKey(StatisticsTypeEnum.INTERFACE_STATISTICS.getDesc());
        final PagerVO pager = PagerVO.getPager(count.intValue(), currentPage, size);
        final List<StatisticsDetailVo> statisticsDetailVos = cacheService.listStatisticsVoByPage(StatisticsTypeEnum.INTERFACE_STATISTICS.getDesc(), PagerVO.getStart(currentPage, size), PagerVO.getEnd(currentPage, size));
        return new StatisticsV0().setPagerVO(pager).setStatisticsDetail(statisticsDetailVos);
    }

    /**
     * 分页获取接口统计数据,自定义key的方式来统计
     * @param  currentPage
     * @param size
     */
    public StatisticsV0 listByKeyAndPage(String key,Integer currentPage,Integer size) {
        final Long count = cacheService.sizeByBizKey(key);
        final PagerVO pager = PagerVO.getPager(count.intValue(), currentPage, size);
        final List<StatisticsDetailVo> statisticsDetailVos = cacheService.listStatisticsVoByPage(key, PagerVO.getStart(currentPage, size), PagerVO.getEnd(currentPage, size));
        return new StatisticsV0().setPagerVO(pager).setStatisticsDetail(statisticsDetailVos);
    }


}
