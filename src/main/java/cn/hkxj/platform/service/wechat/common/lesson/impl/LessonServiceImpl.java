package cn.hkxj.platform.service.wechat.common.lesson.impl;

import cn.hkxj.platform.pojo.Lesson;
import cn.hkxj.platform.pojo.Wechatuser;
import cn.hkxj.platform.service.wechat.common.base.BaseService;
import cn.hkxj.platform.service.wechat.common.lesson.LessonService;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author Yuki
 * @date 2018/7/14 11:51
 */
@Service
public class LessonServiceImpl extends BaseService implements LessonService{

    /**
     * 将通过爬虫获得的课程列表进行封装
     * @param list
     * @return
     */
    private List<Lesson> getLessonsList(List<LinkedTreeMap> list) {
        List<Lesson> lessonList = new ArrayList<>();
        for (LinkedTreeMap<Object, Object> val : list) {
            Lesson lesson = new Lesson();
            for (Map.Entry entry : val.entrySet()) {
                String key = (String) entry.getKey();
                try {
                    switch (key) {
                        case "coursename": {//课程名称
                            String courseName = (String) entry.getValue();
                            lesson.setCourseName(new String(courseName.getBytes(), "UTF-8"));
                            break;
                        }
                        case "qsz": {//qsz会返回例如qsz=3-16周 星期一 {7-8节}的字符串，拆分成week, day, specfic三个字段
                            String str = (String) entry.getValue();
                            String originalStr = (String) entry.getValue();
                            String[] strs = originalStr.split(" ");
                            lesson.setWeek(strs[0]);
                            lesson.setDay(strs[1]);
                            lesson.setSpecific(strs[2]);
                            break;
                        }
                        case "jxdd": {//上课地点
                            String str = (String) entry.getValue();
                            lesson.setClassroom(new String(str.getBytes(), "UTF-8"));
                            break;
                        }
                        case "jsxm": {//讲课教师
                            String str = (String) entry.getValue();
                            lesson.setTeacher(new String(str.getBytes(), "UTF-8"));
                            break;
                        }
                        default:break;
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            lessonList.add(lesson);
        }
        return lessonList;
    }

    private void insertLessons(Map<String, Object> params){

    }

    @Override
    public List<Lesson> getLessonsByClassname(Wechatuser wechatuser) throws IOException {
        List<Lesson> lessonList = null;
        if(lessonList == null || lessonList.size() == 0){
            lessonList = getLessonsList(getAppsSider(wechatuser).getLesson());
            Map<String, Object> params = new HashMap<>();
            params.put("examList", lessonList);
            params.put("classname", wechatuser.getClassname());
            System.out.println(lessonList.size());
//            examMapper.insertExam(params);
        }
        return lessonList;
    }

    public String toText(List<Lesson> lessons){
        StringBuffer buffer = new StringBuffer();
        lessons.forEach(lesson -> {
            buffer.append("课程名称").append(lesson.getCourseName()).append("\n")
                    .append("上课地点").append(lesson.getClassroom()).append("\n")
                     .append("时间:").append(lesson.getWeek()).append(" ").append(lesson.getDay()).append("\n")
                    .append(lesson.getSpecific()).append("\n")
                    .append("教师").append(lesson.getTeacher()).append("\n");
        });
        return buffer.toString();
    }
}
