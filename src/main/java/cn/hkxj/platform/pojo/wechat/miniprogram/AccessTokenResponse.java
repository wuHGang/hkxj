package cn.hkxj.platform.pojo.wechat.miniprogram;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccessTokenResponse extends Response{
    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private Integer expiresIn;
}
