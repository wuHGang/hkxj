package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Teacher;
import cn.hkxj.platform.spider.newmodel.SearchResult;
import cn.hkxj.platform.spider.newmodel.searchclass.ClassInfoSearchResult;
import cn.hkxj.platform.spider.newmodel.searchclass.CourseTimetableSearchResult;
import cn.hkxj.platform.spider.newmodel.searchclass.SearchClassInfoPost;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomPost;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomResult;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchResultWrapper;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchCoursePost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UrpSearchService {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;

    public List<CourseTimetableSearchResult> searchTimetableByCourse(Course course){
        for (List<CourseTimetableSearchResult> resultList : newUrpSpiderService.searchCourseTimeTable(course)) {
            return resultList;
        }
        return Collections.emptyList();
    }

    public List<CourseTimetableSearchResult> searchTeacherCourseTimetable(Teacher teacher){
        for (List<CourseTimetableSearchResult> resultList :
                newUrpSpiderService.searchCourseTimetableByTeacher(teacher.getAccount())) {
            return resultList;
        }
        return Collections.emptyList();
    }

    public void searchUrpCourse(SearchCoursePost searchCoursePost){
        newUrpSpiderService.searchCourseInfo(searchCoursePost);
    }


    public List<SearchClassroomResult> searchUrpClassroom(SearchClassroomPost searchClassroomPost){
        for (SearchResultWrapper<SearchClassroomResult> resultWrapper : newUrpSpiderService.searchClassroomInfo(searchClassroomPost)) {
            return resultWrapper.getPageData().getRecords();
        }
        return Collections.emptyList();
    }

    public List<SearchClassroomResult> searchAllUrpClassroom(){
        SearchClassroomPost post = new SearchClassroomPost();
        post.setExecutiveEducationPlanNum("2019-2020-1-1");
        return searchUrpClassroom(post);
    }


    public List<ClassInfoSearchResult> searchUrpClass(SearchClassInfoPost searchClassInfoPost){
        for (SearchResult<ClassInfoSearchResult> searchResult : newUrpSpiderService.getClassInfoSearchResult(searchClassInfoPost)) {
            return searchResult.getRecords();
        }

        return Collections.emptyList();
    }
}
