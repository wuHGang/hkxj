
package cn.hkxj.platform.pojo.request;

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

    public void validateParam () {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (size == null) {
            size = 10;
        }
    }
}
