package cn.hkxj.platform.spider.model;

import com.google.common.base.MoreObjects;

/**
 * @author JR Chan
 * @date 2019/1/12
 */
public class UrpResult<ResultType> {

    private ResultType data;
    private int status;
    private String message;


    public ResultType getData() {
        return data;
    }

    public void setData(ResultType data) {
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
