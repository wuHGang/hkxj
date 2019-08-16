package cn.hkxj.platform.spider;

import cn.hkxj.platform.pojo.constant.Academy;
import org.junit.Before;
import org.junit.Test;

/**
 * @author JR Chan
 * @date 2019/5/2
 */
public class UrpCourseForSpiderSpiderTest {
    private UrpCourseSpider spider;

    @Before
    public void init() {
        spider = new UrpCourseSpider(2016024249, "1");
    }


    @Test
    public void getAcademyId() {
        UrpCourse urpCourse = spider.getUrpCourse("2206015");
        Academy academy = Academy.getAcademyByName(urpCourse.getAcademyName());
//        System.out.println(StringUtils.isEmpty(" "));
    }
}