package cn.hkxj.platform.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/1 19:56
 */
@Data
public class GradeSearchResult {

    private List<UrpGradeAndUrpCourse> data;

    private boolean update;

}
