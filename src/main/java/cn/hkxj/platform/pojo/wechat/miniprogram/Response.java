package cn.hkxj.platform.pojo.wechat.miniprogram;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Response {
    @SerializedName("errcode")
    private int errcode;
    @SerializedName("errmsg")
    private String errMsg;

}
