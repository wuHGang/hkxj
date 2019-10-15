package cn.hkxj.platform.spider.newmodel.searchteacher;

import cn.hkxj.platform.pojo.constant.Academy;
import cn.hkxj.platform.spider.newmodel.SearchPost;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class SearchTeacherPost extends SearchPost {
    /**
     * 对应的学院编号  参考 {@link Academy#getUrpCode()}
     */
    private String departmentNum = "";
    /**
     * 教师名称
     */
    private String teacherName = "";
}
