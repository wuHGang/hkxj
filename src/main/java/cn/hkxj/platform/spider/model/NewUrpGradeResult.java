package cn.hkxj.platform.spider.model;

import lombok.Data;

import java.util.List;

@Data
public class NewUrpGradeResult {
    private int state;
    private List<NewUrpGrade> list;
}
