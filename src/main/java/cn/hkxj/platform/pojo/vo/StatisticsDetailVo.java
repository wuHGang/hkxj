
package cn.hkxj.platform.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhouqinglai
 * @version version
 * @title StatisticsDetailVo
 * @desc   统计详情dto
 * @date: 2019年05月03日
 */
@Data
@Accessors(chain = true)
public class StatisticsDetailVo {

    /**
     * URL
     */
    private String url;

    /**
     * 每天的调用次数
     */
    private Long count;

    /**
     * 天数
     */
    private String date;

}
