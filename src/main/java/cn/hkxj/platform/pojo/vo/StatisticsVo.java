
package cn.hkxj.platform.pojo.vo;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhouqinglai
 * @version version
 * @title StatisticsVo
 * @desc
 * @date: 2019年05月03日
 */
@Data
@Accessors(chain = true)
public class StatisticsVo {
    private List<StatisticsDetailVo> statisticsDetail;
    private PageVo pageVo;
}
