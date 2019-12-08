package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/14 22:37
 */
@Data
@Accessors(chain = true)
public class NewGrade {

    private UrpGrade urpGrade;

    private List<UrpGradeDetail> details;
}
