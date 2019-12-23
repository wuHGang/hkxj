package cn.hkxj.platform.pojo.vo;

import cn.hkxj.platform.pojo.GradeDetail;
import cn.hkxj.platform.pojo.UrpGradeDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GradeDetailVo {
    /**
     * 课堂成绩
     */
    private GradeDetailVoItem classGrade;
    /**
     * 实践成绩
     */
    private GradeDetailVoItem practiceGrade;

    /**
     * 实验成绩
     */
    private GradeDetailVoItem experimentGrade;

    @Data
    @AllArgsConstructor
    public static class GradeDetailVoItem{
        private String totalGrade;
        private String regularGrade;
        private String midtermGrade;
        private String finalTermGrade;


        public GradeDetailVoItem(UrpGradeDetail urpGradeDetail){
            this(urpGradeDetail.getTotalGrade()== null ? "": urpGradeDetail.getTotalGrade().toString(),
                    urpGradeDetail.getRegularGrade()== null ? "": urpGradeDetail.getRegularGrade().toString(),
                    urpGradeDetail.getMidtermGrade()== null ? "": urpGradeDetail.getMidtermGrade().toString(),
                    urpGradeDetail.getFinaltermGrade()== null ? "": urpGradeDetail.getFinaltermGrade().toString());

        }

    }
}
