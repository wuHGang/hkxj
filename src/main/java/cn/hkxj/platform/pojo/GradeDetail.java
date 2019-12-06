package cn.hkxj.platform.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GradeDetail {

    private Grade grade;
    private List<UrpGradeDetail> urpGradeDetailList;
}
