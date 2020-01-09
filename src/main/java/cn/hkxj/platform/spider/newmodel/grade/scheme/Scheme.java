/**
  * Copyright 2020 bejson.com 
  */
package cn.hkxj.platform.spider.newmodel.grade.scheme;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2020-01-09 1:8:0
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Scheme {

    private List<SchemeGradeItem> cjList;
    private int xqwtg;
    private int xqzms;
    private int xqtgms;
    private int xqzxs;
    private int xqzxf;
    /**
     * 总学分
     */
    @JSONField(name = "zxf")
    private double totalCredits;
    @JSONField(name = "zxs")
    private int totalStudyHour;
    private int yqzxf;
    private int yqxf;
    private double yxxf;
    private int tgms;
    private int wtgms;
    private int zms;
    private String cjlx;
    private String cjbh;
    private int fajhwkcms;
    private int kznzms;
    private int fajhnkcms;
    private int kzxdms;
    private int kzwtgms;
    private int kztgms;
    private int fajhzxf;
    private int fajhzxs;
    private int fajhzms;
    private String famc;
    private String zxjxjhh;

}