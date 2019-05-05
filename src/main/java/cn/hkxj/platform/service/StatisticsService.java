
package cn.hkxj.platform.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.hkxj.platform.pojo.constant.StatisticsTypeEnum;
import cn.hkxj.platform.pojo.vo.PagerVO;
import cn.hkxj.platform.pojo.vo.StatisticsDetailVo;
import cn.hkxj.platform.pojo.vo.StatisticsV0;
import cn.hkxj.platform.utils.DateUtils;

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
        final List<StatisticsDetailVo> statisticsDetailVos = cacheService.listStatisticsVoByPage(StatisticsTypeEnum.INTERFACE_STATISTICS.getDesc(), PagerVO.getStart(currentPage, size), PagerVO
                .getEnd(currentPage, size),false,LocalDateTime.now());
        return new StatisticsV0().setPagerVO(pager).setStatisticsDetail(statisticsDetailVos);
    }

    /**
     * 列出每天或者每个月的统计数据
     * @param currentPage
     * @param size
     * @param time
     * @param isEveryDay
     * @return
     */
    public StatisticsV0 listByEveryDayOrMonth(Integer currentPage,Integer size,String time,boolean isEveryDay) {
        String key = StatisticsTypeEnum.INTERFACE_STATISTICS.getDesc();
        LocalDateTime localDateTime = DateUtils.string2LocalDateTime(time,DateUtils.YYYY_MM_DD_PATTERN);
        final Long countForEveryDayOrMonth = cacheService.count(StatisticsTypeEnum.INTERFACE_STATISTICS.getDesc(),localDateTime,isEveryDay);
        final PagerVO pager = PagerVO.getPager(countForEveryDayOrMonth.intValue(), currentPage, size);
        final List<StatisticsDetailVo> statisticsDetailVos = cacheService.listStatisticsVoByPage(key, PagerVO.getStart(currentPage, size), PagerVO.getEnd(currentPage, size),isEveryDay,localDateTime);
        return new StatisticsV0().setPagerVO(pager).setStatisticsDetail(statisticsDetailVos);
    }


}
