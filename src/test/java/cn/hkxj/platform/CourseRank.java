package cn.hkxj.platform;


import cn.hkxj.platform.pojo.UrpCourse;
import cn.hkxj.platform.service.NewUrpSpiderService;
import cn.hkxj.platform.service.UrpCourseService;
import cn.hkxj.platform.spider.newmodel.course.UrpCourseForSpider;
import cn.hkxj.platform.spider.newmodel.searchcourse.ClassCourseSearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.ClassInfoSearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.Records;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
/*
  1. 总体所有班级的排名
  2. 按照年级排名
  3. 按照专业排名
 */
public class CourseRank {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private UrpCourseService urpCourseService;

    @Test
    public void testRank(){
        ConcurrentHashMap<Records, Pair<Integer, Set<UrpCourse>>> map = new ConcurrentHashMap<>();
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(15, 15, 0L, TimeUnit.MILLISECONDS, queue);
        HashSet<UrpCourse> allCourseSet = new HashSet<>();

        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger();
        for (ClassInfoSearchResult searchResult : newUrpSpiderService.getClassInfoSearchResult("2014025838", "1")) {

            for (Records record : searchResult.getRecords()) {
                threadPoolExecutor.submit(() ->{
                    String classNum = record.getId().getClassNum();
                    System.out.println(classNum);
                    HashSet<UrpCourse> set = new HashSet<>();
                    for (List<ClassCourseSearchResult> resultList : newUrpSpiderService.searchClassTimeTable("2014025838", "1", classNum)) {

                        for (ClassCourseSearchResult result : resultList) {
                            UrpCourse course = urpCourseService.getUrpCourseByCourseId(result.getId().getKch());
                            set.add(course);
                        }
                    }
                    allCourseSet.addAll(set);
                    Pair<Integer, Set<UrpCourse>> pair = Pair.of(classHourSum(set), set);
                    map.put(record, pair);
                    count.getAndIncrement();
                });

            }

        }


//        while (!queue.isEmpty()){
//
//        }

        while (count.get() < 50){

        }
        ArrayList<Map.Entry<Records, Pair<Integer, Set<UrpCourse>>>> list = Lists.newArrayList(map.entrySet());
        list.sort(Comparator.comparingInt(o -> o.getValue().getKey()));


        showRank(list, "所有班级课时数目排行");
        courseRank(allCourseSet);
    }

    private int classHourSum(Set<UrpCourse> set){
        int sum = 0;
        for (UrpCourse course : set) {
            sum = sum + course.getClassHour();
        }
        return sum;
    }

    private void showRank(ArrayList<Map.Entry<Records, Pair<Integer, Set<UrpCourse>>>> list, String title){
        System.out.println(title);
        int rank = 1;
        for (Map.Entry<Records, Pair<Integer, Set<UrpCourse>>> entry : list) {
            System.out.println(rank+ ": "+ entry.getKey().getClassName() + "  课程数"+ entry.getValue().getValue().size() + " 课时:"+entry.getKey() );
            rank ++;
        }

        System.out.println("\n\n");

    }

    private void courseRank(Set<UrpCourse> set){
        int rank = 1;
        System.out.println("本学期所有课程课时排行");
        for (UrpCourse course : set) {
            System.out.println(rank+ ": "+ course.getCourseName() + " "+ course.getClassHour());
            rank ++;
        }
        System.out.println("\n\n");

    }
}
