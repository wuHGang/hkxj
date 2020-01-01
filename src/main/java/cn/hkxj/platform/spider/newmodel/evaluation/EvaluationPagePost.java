package cn.hkxj.platform.spider.newmodel.evaluation;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EvaluationPagePost {
    private String evaluatedPeople;
    private String evaluatedPeopleNumber;
    private String questionnaireCode;
    private String questionnaireName;
    private String evaluationContentNumber;
    private String evaluationContentContent = "";
}
