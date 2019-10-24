package cn.hkxj.platform.elasticsearch;

import cn.hkxj.platform.elasticsearch.document.CourseTimeTableDocument;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CourseTimeTableSearchServiceTest {
    static {
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    @Resource
    private CourseTimeTableSearchService courseTimeTableSearchService;

    @Test
    public void saveTimeTableDate() {
        courseTimeTableSearchService.saveTimeTableDate();
    }

    @Test
    public void search() {
        for (CourseTimeTableDocument document : courseTimeTableSearchService.searchCourseTimeTable(0, 10, "高数")) {
            System.out.println(document);
        }
        ;
    }
}