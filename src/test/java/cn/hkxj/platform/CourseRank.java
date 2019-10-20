package cn.hkxj.platform;


import cn.hkxj.platform.pojo.UrpCourse;
import cn.hkxj.platform.service.NewUrpSpiderService;
import cn.hkxj.platform.service.UrpCourseService;
import cn.hkxj.platform.spider.newmodel.SearchResult;
import cn.hkxj.platform.spider.newmodel.searchclass.ClassInfoSearchResult;
import cn.hkxj.platform.spider.newmodel.searchclass.CourseTimetableSearchResult;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
    public void testRank() throws IOException, InterruptedException {
        ConcurrentHashMap<ClassInfoSearchResult, Pair<Integer, Set<UrpCourse>>> map = new ConcurrentHashMap<>();
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(9, 9, 0L, TimeUnit.MILLISECONDS, queue);
        HashSet<UrpCourse> allCourseSet = new HashSet<>();

        AtomicInteger sizeCount = new AtomicInteger(1);
        List<SearchResult<ClassInfoSearchResult>> classInfoSearchResult = newUrpSpiderService.getClassInfoSearchResult(null);


        int totalSize = classInfoSearchResult.get(0).getRecords().size();

        CountDownLatch latch = new CountDownLatch(totalSize);

        for (SearchResult<ClassInfoSearchResult> searchResult : classInfoSearchResult) {

            for (ClassInfoSearchResult record : searchResult.getRecords()) {
                threadPoolExecutor.submit(() -> {
                    long start = System.currentTimeMillis();
                    try{

                        String classNum = record.getId().getClassNum();
                        HashSet<UrpCourse> set = new HashSet<>();
                        for (List<CourseTimetableSearchResult> resultList : newUrpSpiderService.searchClassTimeTable("2014025838", "1", classNum)) {

                            for (CourseTimetableSearchResult result : resultList) {
                                UrpCourse course = urpCourseService.getUrpCourseByCourseId(result.getId().getCourseId());
                                set.add(course);
                            }
                        }
                        allCourseSet.addAll(set);
                        Pair<Integer, Set<UrpCourse>> pair = Pair.of(classHourSum(set), set);
                        map.put(record, pair);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        System.out.println(sizeCount.getAndIncrement()+ " " + (System.currentTimeMillis() - start));
                        latch.countDown();
                    }

                });

            }

        }
        latch.await();

        handlerClassCourseHour(map);
        courseRank(allCourseSet);
    }

    private void handlerClassCourseHour(Map<ClassInfoSearchResult, Pair<Integer, Set<UrpCourse>>> map){
        ArrayList<Map.Entry<ClassInfoSearchResult, Pair<Integer, Set<UrpCourse>>>> list = Lists.newArrayList(map.entrySet());
        list.sort(((Comparator<Map.Entry<ClassInfoSearchResult, Pair<Integer, Set<UrpCourse>>>>) (o1, o2) -> {
            return Integer.compare(o1.getValue().getKey(), o2.getValue().getKey());
        }).reversed());

        List<ClassCourseHour> data = list.stream()
                .map(x -> new ClassCourseHour(x.getKey(), x.getValue().getKey(), x.getValue().getValue()))
                .collect(Collectors.toList());

        writeFile(JSON.toJSONString(data), "classCourseHour.json");
    }
    private void writeFile(String content, String fileName){
        File file = new File(fileName);
        try {
            Files.write(content.getBytes(),file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int classHourSum(Iterable<UrpCourse> set){
        int sum = 0;
        for (UrpCourse course : set) {
            sum = sum + course.getClassHour();
        }
        return sum;
    }

    @lombok.Data
    @AllArgsConstructor
    private class ClassCourseHour {
        private ClassInfoSearchResult classInfo;
        private int courseHourCount;
        private Set<UrpCourse> urpCourseSet;
    }

    private void courseRank(Set<UrpCourse> set) throws IOException {
        ArrayList<UrpCourse> list = Lists.newArrayList(set);
        list.sort(Comparator.comparingInt(UrpCourse::getClassHour).reversed());
        writeFile(JSON.toJSONString(list), "courseRank.json");
    }
}
