
package cn.hkxj.platform.pojo.request;

import java.time.LocalDateTime;

import cn.hkxj.platform.utils.DateUtils;
import lombok.Data;

/**
 * @author zhouqinglai
 * @version version
 * @title StatisticsRequest
 * @desc
 * @date: 2019年05月03日
 */
@Data
public class StatisticsRequest {
    private Integer currentPage;
    private Integer size;
    private String date;
    private Boolean isEveryDay;

    public void validateParam () {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (size == null) {
            size = 10;
        }
        if (date == null) {
            date = DateUtils.getTimeOfPattern(LocalDateTime.now(), DateUtils.YYYY_MM_DD_PATTERN);
        }
        if (isEveryDay == null) {
            isEveryDay = true;
        }
    }
}
