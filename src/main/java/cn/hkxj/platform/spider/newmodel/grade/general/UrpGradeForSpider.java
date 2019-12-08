package cn.hkxj.platform.spider.newmodel.grade.general;

import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.spider.newmodel.grade.detail.UrpGradeDetailForSpider;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


/**
 * @author Yuki
 * @date 2019/8/14 18:20
 */
@Data
public class UrpGradeForSpider {

    /**
     * 大体成绩
     */
    private UrpGeneralGradeForSpider urpGeneralGradeForSpider;
    /**
     * 详细成绩
     */
    private UrpGradeDetailForSpider urpGradeDetailForSpider;



}
