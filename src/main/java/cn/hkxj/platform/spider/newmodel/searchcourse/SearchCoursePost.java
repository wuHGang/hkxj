package cn.hkxj.platform.spider.newmodel.searchcourse;

import cn.hkxj.platform.spider.newmodel.SearchPost;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class SearchCoursePost extends SearchPost {
    /**
     * 开课学院号
     */
    private String academyCode = "";
    private String courseName = "";
    private String courseNumber = "";
    /**
     * 课序号
     */
    private String courseOrderNumber = "";
    private String courseType = "";
}
