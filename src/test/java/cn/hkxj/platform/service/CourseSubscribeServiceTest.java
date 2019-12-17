package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.dto.CourseTimeTableDetailDto;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.CourseSubscriptionMessage;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/5/9 22:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class CourseSubscribeServiceTest {

    @Resource
    private CourseSubscribeService courseSubscribeService;

    @Test
    public void getCoursesSubscribeForCurrentDay() {

//        for (CourseSubscriptionMessage message : courseSubscribeService.getSubscriptionMessages(CourseSubscribeService.FIRST_SECTION)) {
//            System.out.println(message);
//        }


        List<CourseSubscriptionMessage> collect = courseSubscribeService.getSubscriptionMessages(CourseSubscribeService.FIRST_SECTION).stream()
                .filter(subscriptionMessage -> !Objects.isNull(subscriptionMessage))
                .filter(subscriptionMessage -> !Objects.isNull(subscriptionMessage.getDetailDto()))
                .filter(x-> StringUtils.isNotBlank(x.getPushContent()))
                .collect(Collectors.toList());

        System.out.println(collect.size());
    }

    @Test
    public void getCourseTimeTables() {
        CourseTimeTableDetailDto tablesSection = courseSubscribeService.getCourseTimeTablesSection(2017026003, 1);

        System.out.println(tablesSection);

//        Map<String, Set<CourseGroupMsg>> map = courseSubscribeService.getCoursesSubscribeForCurrentDay();
//        map.forEach((appid, msgs) -> {
//            System.out.println("appid = " + appid);
//            System.out.println(msgs);
//        });
    }
}