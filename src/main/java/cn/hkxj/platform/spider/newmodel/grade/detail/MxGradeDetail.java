package cn.hkxj.platform.spider.newmodel.grade.detail;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Yuki
 * @date 2019/7/31 20:04
 */
@Data
public class MxGradeDetail {

    /**
     * 课序号
     */
    @JSONField(name = "coureSequenceNumber")
    private int courseSequenceNumber;
    /**
     * 总成绩,显示时要向上取整
     */
    private Double zcj;
    /**
     * 平时成绩
     */
    private Double pscj;
    /**
     * 期中成绩
     */
    private Double qzcj;
    /**
     * 期末成绩
     */
    private Double qmcj;
    /**
     * 评论
     */
    private String remark;
    /**
     * 评论1
     */
    private String remark1;
    /**
     * 评论2
     */
    private String remark2;
    /**
     *
     */
    @JSONField(name = "cjxs_")
    private String secondcjxs;
    /**
     *
     */
    @JSONField(name = "pscjxs_")
    private String secondpscjxs;
    /**
     *
     */
    @JSONField(name = "qzcjxs_")
    private String secondqzcjxs;
    /**
     *
     */
    @JSONField(name = "qmcjxs_")
    private String secondqmcjxs;
    /**
     *
     */
    @JSONField(name = "cjxs")
    private String firstcjxs;
    /**
     *
     */
    @JSONField(name = "pscjxs")
    private String firstpscjxs;
    /**
     *
     */
    @JSONField(name = "qzcjxs")
    private String firstqzcjxs;
    /**
     *
     */
    @JSONField(name = "qmcjxs")
    private String firstqmcjxs;

}
