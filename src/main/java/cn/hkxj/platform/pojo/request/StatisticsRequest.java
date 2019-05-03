
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
    private Long findDate;
    private Boolean isEveryDay;

    public void validateParam () {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (size == null) {
            size = 10;
        }
        if (findDate == null) {
            findDate = DateUtils.getTimeStamp(LocalDateTime.now());
        }
        if (isEveryDay == null) {
            isEveryDay = true;
        }
    }
}
