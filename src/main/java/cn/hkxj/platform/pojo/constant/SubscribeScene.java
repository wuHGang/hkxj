package cn.hkxj.platform.pojo.constant;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author Yuki
 * @date 2019/5/25 12:52
 * 订阅的场景值
 */
public enum SubscribeScene {

    COURSE_PUSH("课表推送", "1005"),
    GRADE_AUTO_UPDATE("成绩推送", "1010"),
    EXAM_SUBSCRUBE("考试推送","1015");

    private String chinese;

    private String scene;

    SubscribeScene(String chinese, String scene){
        this.chinese = chinese;
        this.scene = scene;
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

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
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
