package cn.hkxj.platform.spider.newmodel.coursetimetable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/29 23:05
 */
@Data
public class UrpCourseTimeTableForSpider {
    /**
     * 所有学分
     */
    private Double allUnits;

    /**
     * 所有的课程时间表，包括信息和详情
     */
    @JSONField(name = "xkxx")
    private List<HashMap<String, UrpCourseTimeTable>> details;

}
