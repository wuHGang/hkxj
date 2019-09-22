package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.EmptyRoom;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.timetable.RoomTimeTable;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.newmodel.emptyroom.EmptyRoomPojo;
import cn.hkxj.platform.spider.newmodel.emptyroom.EmptyRoomPost;
import cn.hkxj.platform.spider.newmodel.emptyroom.EmptyRoomRecord;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.HashMultimap;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Xie
 * @date 2019/9/18
 */
@Slf4j
@Service("emptyRoomService")
@CacheConfig(cacheNames = "empty_Room_data")
public class EmptyRoomService {

    @Autowired
    EmptyRoomService _this;

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 从缓存中获取空教室信息，若redis中没有相关缓存，则爬取
     *
     * @param week
     * @param teaNum
     * @param wSection
     * @return
     */
    @Cacheable(key = "#p0+#p1+#p2", unless = "#result == null")
    public List<String> getEmptyRoomReply(String week, String teaNum, String wSection) {
        log.info("爬取空教室缓存{} {} {}", week, teaNum, wSection);
        NewUrpSpider spider = new NewUrpSpider("2016024254", "1");

        EmptyRoomPost emptyRoomPost = new EmptyRoomPost(week, teaNum, wSection, "1", "50");
        EmptyRoomPojo emptyRoomPojo = spider.getEmptyRoom(emptyRoomPost);
        List<String> records = new ArrayList<>();
        for (EmptyRoomRecord emptyRoomRecord : emptyRoomPojo.getRecords()) {
            records.add(emptyRoomRecord.getClassroomName());
        }

        //times是还需爬取数据的次数，教务网只能一页显示50个数据，需要循环爬取直到爬完
        int times = emptyRoomPojo.getPageContext().getTotalCount() / 50;
        //获取剩余的数据
        for (int i = 2; i <= times + 1; i++) {
            emptyRoomPost = new EmptyRoomPost(week, teaNum, wSection, String.valueOf(times), "50");
            emptyRoomPojo = spider.getEmptyRoom(emptyRoomPost);
            for (EmptyRoomRecord emptyRoomRecord : emptyRoomPojo.getRecords()) {
                records.add(emptyRoomRecord.getClassroomName());
            }
        }

        return records;

    }

    /**
     * 对数据进行楼层筛选
     *
     * @param week
     * @param teaNum
     * @param wSection
     * @param floor
     * @return
     */
    public List<String> getEmptyRoomReply(String week, String teaNum, String wSection, int floor) {
        // 注解@Cacheable是使用AOP代理实现的 ，通过创建内部类来代理缓存方法
        // 类内部的方法调用类内部的缓存方法不会走代理，使得cacheable注解失效，
        // 所以就不能正常创建缓存，因此需要一个代理对象来调用
        List<String> emptyRoomList = _this.getEmptyRoomReply(week, teaNum, wSection);
        String key = "empty_Room_data::" + week + teaNum + wSection;
        //对数据缓存24小时，重复查询会更新这个数据的过期时间
        redisTemplate.expire(key, 30, TimeUnit.HOURS);
        List<String> result = new ArrayList<>();
        for (String s : emptyRoomList) {
            System.out.println(s);
            if (checkFloor(s, floor)) {
                result.add(s);
            }
        }
        return result;
    }


    /**
     * 判断楼层
     */
    private boolean checkFloor(String className, int floor) {
        if (floor == 0) {
            return true;
        }
        char[] chars = className.replaceAll("\\D", "").toCharArray();
        if (chars.length != 4) {
            return false;
        }
        int floorTemp = (chars[0] - '0') * 10 + (chars[1] - '0');
        if (floorTemp == floor) {
            return true;
        } else {
            return false;
        }
    }
}

