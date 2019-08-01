package cn.hkxj.platform.spider.newmodel;

import lombok.Data;

/**
 * @author Yuki
 * @date 2019/8/1 17:32
 */
@Data
public class UrpCourse {
    /**
     * 课程号
     */
    private String kch;
    /**
     * 课程名称
     */
    private String kcm;
    /**
     * 学院编号
     */
    private Integer xsh;
    /**
     * 学院名称
     */
    private String xsm;
    /**
     * 学分
     */
    private Double xf;

}
