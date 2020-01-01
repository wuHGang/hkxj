package cn.hkxj.platform.spider.newmodel.evaluation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EvaluationPost {

    private String tokenValue = "";
    private String questionnaireCode;
    private String evaluationContentNumber;
    private String evaluatedPeopleNumber;
    private String count = "";
    @JSONField(name = "0000000050")
    private String first = "20_1";
    @JSONField(name = "0000000051")
    private String second = "30_1";
    @JSONField(name = "0000000052")
    private String third = "20_1";
    @JSONField(name = "0000000053")
    private String fourth = "30_1";
    @JSONField(name = "zgpj")
    private String comment = "关爱学生、认真负责、讲课投入,内容充实、条理清晰、重点突出";

    public static void main(String[] args) {
        EvaluationPost post = new EvaluationPost();
        System.out.println(JSON.toJSON(post));
    }
}
