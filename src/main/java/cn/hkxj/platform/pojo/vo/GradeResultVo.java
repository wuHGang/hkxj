package cn.hkxj.platform.pojo.vo;

import cn.hkxj.platform.pojo.Grade;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GradeResultVo {

    private List<TermGradeVo> termGradeList;

    private Double gpa;

    private Double optionalCourseCredit;
    /**
     * 这个errorCode 是针对抓取失败时的错误信息给前端提示，譬如超时或者未评估等等
     */
    private int errorCode;

    private String message;

}
