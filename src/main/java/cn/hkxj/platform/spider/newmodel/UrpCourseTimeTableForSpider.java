package cn.hkxj.platform.spider.newmodel;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yuki
 * @date 2019/8/29 23:05
 */
@Data
public class UrpCourseTimeTableForSpider {

    private Double allUnits;

    private List<LinkedHashMap<String,UrpCourseTimeTable>> xkxx;
}
