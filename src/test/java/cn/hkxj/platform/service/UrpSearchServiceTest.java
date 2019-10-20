package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.CourseDao;
import cn.hkxj.platform.dao.CourseTimeTableDao;
import cn.hkxj.platform.dao.TeacherDao;
import cn.hkxj.platform.dao.UrpClassRoomDao;
import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.newmodel.searchclass.ClassInfoSearchResult;
import cn.hkxj.platform.spider.newmodel.searchclass.CourseTimetableSearchResult;
import cn.hkxj.platform.spider.newmodel.searchclass.SearchClassInfoPost;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomResult;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UrpSearchServiceTest {
    @Resource
    private UrpSearchService urpSearchService;
    @Resource
    private UrpClassRoomDao urpClassRoomDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private CourseTimeTableDao courseTimeTableDao;
    @Resource
    private TeacherCourseMapper teacherCourseMapper;
    @Resource
    private TeacherCourseTimetableMapper teacherCourseTimetableMapper;
    @Resource
    private ClassCourseMapper classCourseMapper;
    @Resource
    private ClassCourseTimetableMapper classCourseTimetableMapper;
    @Resource
    private UrpClassMapper urpClassMapper;

    @Test
    public void searchTimetableByCourse() {
        for (Course course : courseDao.getAllCourse()) {
            for (CourseTimetableSearchResult courseTimetableSearchResult : urpSearchService.searchTimetableByCourse(course)) {
                UrpClassroom urpClassroom = courseTimetableSearchResult.transToUrpClassRoom();
                if (urpClassRoomDao.selectByClassroom(urpClassroom).size() == 0) {
                    urpClassRoomDao.insertSelective(urpClassroom);
                }
            }

        }

    }

    @Test
    public void searchUrpCourse() {
    }

    @Test
    public void searchUrpClassInfo() {
        SearchClassInfoPost post = new SearchClassInfoPost();
        post.setExecutiveEducationPlanNum("2019-2020-1-1");
        for (ClassInfoSearchResult result : urpSearchService.searchUrpClass(post)) {
            urpClassMapper.insertSelective(result.transToUrpClass());
        }

    }

    @Test
    public void searchUrpClassroom() {
        UrpClassroom urpClassroom = new UrpClassroom()
                .setNumber("01017");
        List<UrpClassroom> list = urpClassRoomDao.selectByClassroom(urpClassroom);
        System.out.println(list);
    }

    @Test
    public void searchAllUrpClassroom() {
        for (SearchClassroomResult result : urpSearchService.searchAllUrpClassroom()) {
            if (urpClassRoomDao.selectByClassroom(result.transToUrpClassRoom()).size() == 0) {
                urpClassRoomDao.insertSelective(result.transToUrpClassRoom());
            }
        }

    }

    @Test
    public void searchTeacherCourseTimetable() {

        HashSet<TeacherCourse> teacherCourses = new HashSet<>();
        HashSet<TeacherCourseTimetable> teacherCourseTimetables = new HashSet<>();
        HashSet<ClassCourseTimetable> classCourseTimetables = new HashSet<>();
        HashSet<ClassCourse> classCourses = new HashSet<>();

        Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();

        for (Teacher teacher : teacherDao.getAllTeacher()) {
            for (CourseTimetableSearchResult courseTimetableSearchResult :
                    urpSearchService.searchTeacherCourseTimetable(teacher)) {
                List<String> classIdList = Collections.emptyList();

                if (courseTimetableSearchResult.getClassIdList() != null) {
                    classIdList = splitter.splitToList(courseTimetableSearchResult.getClassIdList());
                }

                for (CourseTimetable courseTimetable : courseTimetableSearchResult.transToCourseTimetable()) {
                    List<CourseTimetable> courseTimetableList = courseTimeTableDao.selectByCourseTimetable(courseTimetable);
                    classCourses.addAll(classIdList.stream()
                            .map(x -> new ClassCourse()
                                    .setClassId(x)
                                    .setCourseId(courseTimetable.getCourseId())
                                    .setCourseOrder(courseTimetable.getCourseSequenceNumber())
                                    .setTermYear(courseTimetable.getTermYear())
                                    .setTermOrder(courseTimetable.getTermOrder())
                            ).collect(Collectors.toList()));

                    teacherCourses.add(new TeacherCourse()
                            .setTeacherId(teacher.getAccount())
                            .setCourseId(courseTimetable.getCourseId())
                            .setCourseOrder(courseTimetable.getCourseSequenceNumber())
                            .setTermYear(courseTimetable.getTermYear())
                            .setTermOrder(courseTimetable.getTermOrder()));


                    // 这里19级的课数据库中没有对应的记录 应该插入一条新的记录

                    if (courseTimetableList.size() == 0) {
                        log.error("缺失数据 {}", courseTimetableList);
                        courseTimeTableDao.insertSelective(courseTimetable);
                        classCourseTimetables.addAll(classIdList.stream()
                                .map(x -> new ClassCourseTimetable()
                                        .setClassId(x)
                                        .setCourseTimetableId(courseTimetable.getId())
                                        .setTermYear(courseTimetable.getTermYear())
                                        .setTermOrder(courseTimetable.getTermOrder())
                                ).collect(Collectors.toList()));
                    } else if (courseTimetableList.size() == 1) {
                        classCourseTimetables.addAll(classIdList.stream()
                                .map(x -> new ClassCourseTimetable()
                                        .setClassId(x)
                                        .setCourseTimetableId(courseTimetableList.get(0).getId())
                                        .setTermYear(courseTimetable.getTermYear())
                                        .setTermOrder(courseTimetable.getTermOrder())
                                ).collect(Collectors.toList()));

                        teacherCourseTimetables.add(new TeacherCourseTimetable()
                                .setCourseTimetableId(courseTimetableList.get(0).getId())
                                .setTeacherId(teacher.getId())
                                .setTermYear(courseTimetable.getTermYear())
                                .setTermOrder(courseTimetable.getTermOrder()));
                    }
                    //这里对应的是某节课存在跨周上课的问题
                    else {
                        for (CourseTimetable tableDetail : courseTimetableList) {
                            System.out.println(tableDetail);
                        }
                    }
                }


            }

        }


        for (TeacherCourse teacherCourse : teacherCourses) {
            teacherCourseMapper.insertSelective(teacherCourse);
        }
        for (TeacherCourseTimetable teacherCourseTimetable : teacherCourseTimetables) {
            teacherCourseTimetableMapper.insertSelective(teacherCourseTimetable);
        }
        for (ClassCourseTimetable classCourseTimetable : classCourseTimetables) {
            classCourseTimetableMapper.insertSelective(classCourseTimetable);
        }
        for (ClassCourse classCourse : classCourses) {
            classCourseMapper.insertSelective(classCourse);
        }


    }
}