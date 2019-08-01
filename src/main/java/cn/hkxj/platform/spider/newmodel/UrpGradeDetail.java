package cn.hkxj.platform.spider.newmodel;

import lombok.Data;

import java.util.List;

/**
 * @author Yuki
 * @date 2019/7/31 19:52
 */
@Data
public class UrpGradeDetail {
    /**
     * 总共有3个元素，第一个是平时成绩，第二个是实验成绩，第三个是时间成绩
     */
    private List<GradeDetail> mx;
}
