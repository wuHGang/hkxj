package cn.hkxj.platform.pojo.wechat;

import lombok.Data;

import java.util.Date;

@Data
public class SubscribeOpenid {
    private Integer id;

    private String openid;

    private Integer subType;

    private Byte isSend;

    private Date gmtCreate;

}