package cn.hkxj.platform.spider.newmodel.searchclassroom;

import cn.hkxj.platform.spider.newmodel.SearchPost;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = false)
@Data
@Accessors( chain = true)
public class SearchClassroomPost extends SearchPost {
    /**
     * 校区  现在只有主校区
     */
    private String campusNumber="";
    /**
     * 教学楼对应的编号
     */
    private String teachingBuildingNumber="";
    /**
     * 课室编号
     */
    private String classRoomNumber="";
}
