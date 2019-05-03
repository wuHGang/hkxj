/*
 *Baijiahulian.comInc.Copyright(c)2014-2019AllRightsReserved.
 */
package cn.hkxj.platform.pojo.vo;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhouqinglai
 * @version version
 * @title StatisticsV0
 * @desc
 * @date: 2019年05月03日
 */
@Data
@Accessors(chain = true)
public class StatisticsV0 {
    private List<StatisticsDetailVo> statisticsDetail;
    private PagerVO pagerVO;
}
