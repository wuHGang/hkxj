package cn.hkxj.platform.spider.newmodel;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author junrong.chen
 */
@Data
@Accessors(chain = true)
public class UrpExamTime {
    /**
     * 科目名称
     */
    private String courseName;
    /**
     * 考试名称
     */
    private String examName;
    /**
     * 考试日期
     */
    private String date;
    /**
     * 星期
     */
    private String week;
    /**
     * 第几周
     */
    private String weekOfTerm;
    /**
     * 考试时间
     */
    private String examTime;
    /**
     *  地点
     */
    private String location;
    /**
     * 座位号
     */
    private String seatNumber;
    /**
     *  准考证号
     */
    private String admissionTicket;

}
