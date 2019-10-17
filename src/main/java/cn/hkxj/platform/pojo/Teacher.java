package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Teacher {
    private Integer id;

    private String account;

    private String name;

    private Integer academy;

    private String sex;

    private String professionalTitle;

    private Date gmtCreate;

    private Date gmtModified;

}