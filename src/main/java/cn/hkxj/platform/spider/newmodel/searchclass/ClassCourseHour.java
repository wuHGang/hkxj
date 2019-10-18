package cn.hkxj.platform.spider.newmodel.searchclass;

import cn.hkxj.platform.pojo.UrpCourse;
import lombok.AllArgsConstructor;

import java.util.Set;

@lombok.Data
@AllArgsConstructor
public class ClassCourseHour {
    public ClassCourseHour(){

    }
    private Records classInfo;
    private int courseHourCount;
    private Set<UrpCourse> urpCourseSet;
}