package cn.hkxj.platform.spider.newmodel.grade.general;

import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.spider.newmodel.grade.detail.UrpGradeDetailForSpider;
import lombok.Data;


/**
 * @author Yuki
 * @date 2019/8/14 18:20
 */
@Data
public class UrpGradeForSpider {

    /**
     * 大体成绩
     */
    private UrpGeneralGradeForSpider urpGeneralGradeForSpider;
    /**
     * 详细成绩
     */
    private UrpGradeDetailForSpider urpGradeDetailForSpider;


    public Grade convertToGrade() {
        return new Grade()
                .setAccount(Integer.parseInt(urpGeneralGradeForSpider.getId().getStudentNumber()))
                .setCourseNumber(urpGeneralGradeForSpider.getId().getCourseNumber())
                .setCourseOrder(urpGeneralGradeForSpider.getId().getCourseSequenceNumber())
                .setScore(urpGeneralGradeForSpider.getCourseScore() == null ? -1 : urpGeneralGradeForSpider.getCourseScore())
                .setGradePoint(urpGeneralGradeForSpider.getGradePoint())
                .setLevelName(urpGeneralGradeForSpider.getLevelName())
                .setLevelPoint(urpGeneralGradeForSpider.getLevlePoint())
                .setRank(urpGeneralGradeForSpider.getRank())
                .setReplaceCourseNumber(urpGeneralGradeForSpider.getReplaceCourseNumber())
                .setRemark(urpGeneralGradeForSpider.getRemark())
                .setRetakeCourseMark(urpGeneralGradeForSpider.getRetakeCourseMark())
                .setRetakecourseModeCode(urpGeneralGradeForSpider.getRetakeCourseModeCode())
                .setRetakeCourseModeExplain(urpGeneralGradeForSpider.getRetakeCourseModeExplain())
                .setUnpassedReasonCode(urpGeneralGradeForSpider.getUnpassedReasonCode())
                .setUnpassedReasonExplain(urpGeneralGradeForSpider.getUnpassedReasonExplain())
                .setStandardPoint(urpGeneralGradeForSpider.getStandardPoint())
                .setTermYear(urpGeneralGradeForSpider.getId().getTermYear())
                .setTermOrder(urpGeneralGradeForSpider.getId().getTermOrder())
                .setExamTime(urpGeneralGradeForSpider.getId().getExamtime())
                .setOperateTime(urpGeneralGradeForSpider.getOperatetime())
                .setOperator(urpGeneralGradeForSpider.getOperator())
                ;
    }

}
