package cn.hkxj.platform.spider.newmodel.searchcourse;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SearchClassInfoPost {
    /**
     * 这个是个默认值  具体作用未知
     */
    private String paramValue = "100024";
    /**
     * 查询的对应的学期，查询全部则为空
     */
    private String executiveEducationPlanNum = "";
    /**
     * 查询对应的年级，查询全部填空
     */
    private String yearNum = "";
    /**
     * 查询对应的专业，查询全部填空
     */
    private String departmentNum = "";
    private String classNum = "";
    /**
     * 页数
     */
    private String pageNum = "1";
    /**
     * 每页的大小
     */
    private String pageSize = "10000";
}
