package cn.hkxj.platform;


import cn.hkxj.platform.pojo.UrpCourse;
import cn.hkxj.platform.pojo.constant.Academy;
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
import java.io.*;
import java.nio.charset.StandardCharsets;
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
    public void testRank() throws IOException, InterruptedException {
        ConcurrentHashMap<Records, Pair<Integer, Set<UrpCourse>>> map = new ConcurrentHashMap<>();
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(9, 9, 0L, TimeUnit.MILLISECONDS, queue);
        HashSet<UrpCourse> allCourseSet = new HashSet<>();

        AtomicInteger sizeCount = new AtomicInteger(1);
        List<ClassInfoSearchResult> classInfoSearchResult = newUrpSpiderService.getClassInfoSearchResult("2014025838", "1");

        int totalSize = classInfoSearchResult.get(0).getRecords().size();

        CountDownLatch latch = new CountDownLatch(totalSize);

        for (ClassInfoSearchResult searchResult : classInfoSearchResult) {

            for (Records record : searchResult.getRecords()) {
                threadPoolExecutor.submit(() -> {
                    long start = System.currentTimeMillis();
                    try{

                        String classNum = record.getId().getClassNum();
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
        ArrayList<Map.Entry<Records, Pair<Integer, Set<UrpCourse>>>> list = Lists.newArrayList(map.entrySet());
        list.sort(((Comparator<Map.Entry<Records, Pair<Integer, Set<UrpCourse>>>>) (o1, o2) -> {
            return Integer.compare(o1.getValue().getKey(), o2.getValue().getKey());
        }).reversed());


        showRank(list, "所有班级课时数目排行");
        courseRank(allCourseSet);
    }

    private int classHourSum(Iterable<UrpCourse> set){
        int sum = 0;
        for (UrpCourse course : set) {
            sum = sum + course.getClassHour();
        }
        return sum;
    }

    private void showRank(ArrayList<Map.Entry<Records, Pair<Integer, Set<UrpCourse>>>> list, String title) throws IOException {
        System.out.println(title);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("classCourse"),
                StandardCharsets.UTF_8));


        int rank = 1;
        for (Map.Entry<Records, Pair<Integer, Set<UrpCourse>>> entry : list) {
            out.write(String.join(" ",
                    Integer.toString(rank),
                    entry.getKey().getDepartmentNum(),
                    entry.getKey().getDepartmentName(),
                    entry.getKey().getSubjectNum(),
                    entry.getKey().getSubjectName(),
                    entry.getKey().getId().getClassNum(),
                    entry.getKey().getClassName(),
                    Integer.toString(entry.getValue().getValue().size()),
                    entry.getValue().getKey().toString()));
            out.newLine();

            rank ++;
        }

        System.out.println("\n\n");
        out.flush();
        out.close();
    }



    private void courseRank(Set<UrpCourse> set) throws IOException {
        int rank = 1;
        System.out.println("本学期所有课程课时排行");
        ArrayList<UrpCourse> list = Lists.newArrayList(set);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("course"),
                StandardCharsets.UTF_8));


        list.sort(Comparator.comparingInt(UrpCourse::getClassHour).reversed());
        for (UrpCourse course : list) {
            try {
                out.write(String.join(" ",
                        Integer.toString(rank),
                        course.getAcademy().toString(),
                        Academy.getAcademyByCode(course.getAcademy()).getAcademyName(),
                        course.getCourseName(),
                        course.getClassHour().toString()
                ));
                out.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(String.join(" ",
                    Integer.toString(rank),
                    course.getAcademy().toString(),
                    Academy.getAcademyByCode(course.getAcademy()).getAcademyName(),
                    course.getCourseName(),
                    course.getClassHour().toString()
                    )
            );

//            System.out.println(rank+ ": "+ course.getCourseName() + " "+ course.getClassHour());
            rank ++;
        }
        System.out.println("\n\n");
        out.flush();
        out.close();
    }
}
