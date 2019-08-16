package cn.hkxj.platform.spider.newmodel;

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
    private int coureSequenceNumber;
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
    private String cjxs_;
    /**
     *
     */
    private String pscjxs_;
    /**
     *
     */
    private String qzcjxs_;
    /**
     *
     */
    private String qmcjxs_;
    /**
     *
     */
    private String cjxs;
    /**
     *
     */
    private String pscjxs;
    /**
     *
     */
    private String qzcjxs;
    /**
     *
     */
    private String qmcjxs;

}
