package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.mapper.TeacherClassMapper;
import cn.hkxj.platform.mapper.TeacherCourseMapper;
import cn.hkxj.platform.mapper.TeacherMapper;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.Teacher;
import cn.hkxj.platform.pojo.TeacherClass;
import cn.hkxj.platform.pojo.TeacherCourse;
import cn.hkxj.platform.pojo.constant.Academy;
import cn.hkxj.platform.spider.newmodel.SearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.ClassCourseSearchResult;
import cn.hkxj.platform.spider.newmodel.searchteacher.SearchTeacherPost;
import cn.hkxj.platform.spider.newmodel.searchteacher.SearchTeacherResult;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeacherService {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private StudentDao studentDao;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private TeacherClassMapper teacherClassMapper;
    @Resource
    private TeacherCourseMapper teacherCourseMapper;


    public void parseTeacherData(){
        SearchTeacherPost post = new SearchTeacherPost();
        post.setExecutiveEducationPlanNum("2019-2020-1-1");

        for (SearchResult<SearchTeacherResult> result : newUrpSpiderService.searchTeacherInfo("2017023081", "1", post)) {
            for (SearchTeacherResult record : result.getRecords()) {
                Teacher teacher = spiderDataToPojo(record);
                saveTeacherDate(teacher);
            }
        }
    }

    @Transactional
    public void saveTeacherDate(Teacher teacher){
        Splitter splitter = Splitter.on(",").omitEmptyStrings().trimResults();

        Student student = studentDao.selectStudentByAccount(2014025838);

        HashSet<TeacherClass> classNumSet = new HashSet<>();
        HashSet<TeacherCourse> courseIdSet = new HashSet<>();

        for (List<ClassCourseSearchResult> searchResults : newUrpSpiderService.getUrpCourseTimeTableByTeacherAccount(student.getAccount().toString(),
                student.getPassword(), teacher.getAccount())) {
            for (ClassCourseSearchResult result : searchResults) {

                String termName = result.getTermName();
                String termYear = termName.substring(0, 9);
                int termOrder = getTermOrder(termName);
                if(result.getClassIdList() == null){
                    continue;
                }

                List<TeacherClass> teacherClassList = splitter.splitToList(result.getClassIdList()).stream()
                        .map(x -> new TeacherClass()
                                .setClassId(Integer.parseInt(x))
                                .setTeacherId(teacher.getAccount())
                                .setTermOrder(termOrder)
                                .setTermYear(termYear))
                        .collect(Collectors.toList());

                classNumSet.addAll(teacherClassList);
                String courseId = result.getId().getCourseId();

                courseIdSet.add(new TeacherCourse()
                        .setTeacherId(teacher.getAccount())
                        .setTermOrder(termOrder)
                        .setTermYear(termYear)
                        .setCourseId(courseId));

            }
        }
        teacherMapper.insertSelective(teacher);
        for (TeacherClass teacherClass : classNumSet) {
            teacherClassMapper.insertSelective(teacherClass);
        }
        for (TeacherCourse teacherCourse : courseIdSet) {
            teacherCourseMapper.insertSelective(teacherCourse);
        }

    }

    private int getTermOrder(String termName){
        if(termName.contains("一")){
            return 1;
        }else {
            return 2;
        }

    }

    private Teacher spiderDataToPojo(SearchTeacherResult record){

        Academy academy = Academy.getAcademyByUrpCode(Integer.parseInt(record.getDepartmentNum()));

        return
                new Teacher().setAccount(record.getId().getTeacherNumber())
                .setSex(record.getSex())
                .setName(record.getTeacherName())
                .setAcademy(academy.getAcademyCode())
                .setProfessionalTitle(record.getProfessionalTitle());
    }
}
