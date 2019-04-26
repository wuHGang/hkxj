package cn.hkxj.platform.spider.model;

import com.google.common.base.MoreObjects;

/**
 * @author junrong.chen
 * @date 2019/4/26
 */
public class EverGradeResult {
    private EverGrade data;
    private int status;
    private String message;


    public EverGrade getData() {
        return data;
    }

    public void setData(EverGrade data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("data", data)
                .add("status", status)
                .add("message", message)
                .toString();
    }
}
