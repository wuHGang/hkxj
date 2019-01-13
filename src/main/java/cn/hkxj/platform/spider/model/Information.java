package cn.hkxj.platform.spider.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.base.MoreObjects;

/**
 * @author JR Chan
 * @date 2019/1/13
 */
public class Information {
    @JSONField(name = "information")
    private UrpStudentInfo urpStudentInfo;

    public UrpStudentInfo getUrpStudentInfo() {
        return urpStudentInfo;
    }

    public void setUrpStudentInfo(UrpStudentInfo urpStudentInfo) {
        this.urpStudentInfo = urpStudentInfo;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("urpStudentInfo", urpStudentInfo)
                .toString();
    }
}
