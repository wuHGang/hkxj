package cn.hkxj.platform.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Classes {
    private Integer id;

    private String name;

    private Integer academy;

    private Integer subject;

    private Integer year;

    private Integer num;

    private List<Integer> courseTimeTableIds;

}