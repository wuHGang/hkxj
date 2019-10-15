package cn.hkxj.platform.spider.newmodel.searchcourse;

import cn.hkxj.platform.spider.newmodel.SearchPost;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class SearchClassInfoPost extends SearchPost {
    /**
     * 这个是个默认值  具体作用未知
     */
    private String paramValue = "100024";
    /**
     * 查询对应的年级，查询全部填空
     */
    private String yearNum = "";
    /**
     * 查询对应的专业，查询全部填空
     */
    private String departmentNum = "";
    private String classNum = "";
}
