package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yuki
 * @date 2019/8/16 10:05
 */
@Data
@Accessors(chain = true)
public class UrpGradeAndUrpCourse {

    private NewGrade newGrade;

    private UrpCourse urpCourse;

    private Term term;
}
