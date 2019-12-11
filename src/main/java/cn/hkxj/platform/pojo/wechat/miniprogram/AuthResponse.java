package cn.hkxj.platform.pojo.wechat.miniprogram;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(callSuper = true)
@Data
public class AuthResponse extends Response {

    private String openid;

    @SerializedName("session_key")
    private String sessionKey;

    @SerializedName("unionid")
    private String unionId;

}
