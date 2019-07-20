package cn.hkxj.platform.spider.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * @author JR Chan
 * @date 2019/1/13
 */
public class CurrentGrade {
    @JSONField(name = "currentGrade.json")
    private List<UrpGrade> urpGradeList;

    public List<UrpGrade> getUrpGradeList() {
        return urpGradeList;
    }

    public void setUrpGradeList(List<UrpGrade> currentGrade) {
        this.urpGradeList = currentGrade;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("currentGrade.json", urpGradeList)
                .toString();
    }
}
