package cn.hkxj.platform.pojo.constant;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author Yuki
 * @date 2019/5/25 12:52
 * 订阅的场景值
 */
public enum SubscribeScene {

    COURSE_PUSH("课表推送", "1005", ""),
    GRADE_AUTO_UPDATE("成绩推送", "1010", "dmE0nyulM8OVcUs-KojDxCYECrKTmzOGDkEUUm2T5UE"),
    EXAM_SUBSCRUBE("考试推送","1015", "");

    private String chinese;

    private String scene;

    private String miniProgramTemplateId;

    SubscribeScene(String chinese, String scene, String miniProgramTemplateId){
        this.chinese = chinese;
        this.scene = scene;
        this.miniProgramTemplateId = miniProgramTemplateId;
    }

    public static SubscribeScene getSubscribeSceneByChinese(String chinese){
        switch (chinese){
            case "课表推送":
                return COURSE_PUSH;
            case "成绩推送":
                return GRADE_AUTO_UPDATE;
            case "考试推送":
                return EXAM_SUBSCRUBE;
            default:
                return null;
        }
    }

    public static SubscribeScene getByMiniProgramTemplateId(String miniProgramTemplateId){
        // TODO  我这里偷懒这样写 后面改的同学按照规范来
        if(miniProgramTemplateId.equals(GRADE_AUTO_UPDATE.miniProgramTemplateId)){
            return GRADE_AUTO_UPDATE;
        }
        return null;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("chinese", chinese)
                .add("scene", scene)
                .toString();
    }
}
