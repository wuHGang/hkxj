package cn.hkxj.platform.spider.newmodel.grade;

import cn.hkxj.platform.spider.newmodel.grade.general.UrpGradeForSpider;
import lombok.Data;

import java.util.List;

/**
 * @author Yuki
 * @date 2019/7/31 17:22
 */
@Data
public class CurrentGrade {

    private List<UrpGradeForSpider> list;
}
