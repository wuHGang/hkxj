package cn.hkxj.platform.spider.newmodel;

import lombok.Data;

/**
 * @author Yuki
 * @date 2019/8/14 17:58
 * 每一个XsGradeDetail都是一个成绩
 * scoreCoefficient是当前成绩在总成绩中占的比重
 * 而usualScoreCoefficient、midtermScoreCoefficient和endtermScoreCoefficient
 * 都是在当前成绩中占的比重
 */
@Data
public class XsGradeDetail {

    /**
     * 成绩相关信息
     */
    private CourseRelativeInfo id;
    /**
     * 平时成绩系数
     * 示例： "usualScoreCoefficient": ".5"
     * 可为空
     */
    private String usualScoreCoefficient;
    /**
     * 期中考试系数
     * 示例： "midtermScoreCoefficient": ".5"
     * 可为空
     */
    private String midtermScoreCoefficient;
    /**
     * 期末考试系数
     * 示例： "endtermScoreCoefficient": ".5"
     * 可为空
     */
    private String endtermScoreCoefficient;
    /**
     * 分数系数
     * 示例："scoreCoefficient": "1"
     * 可为空
     */
    private String scoreCoefficient;
    /**
     * 备注
     */
    private String remark;
}
