package cn.hkxj.platform.spider.newmodel;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SearchPost {
    /**
     * 查询的对应的学期，查询全部则为空
     */
    private String executiveEducationPlanNum = "";
    /**
     * 页数
     */
    private String pageNum = "1";
    /**
     * 每页的大小
     */
    private String pageSize = "10000";
}
