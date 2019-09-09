package cn.hkxj.platform.spider.newmodel.coursetimetable;

import cn.hkxj.platform.pojo.CourseTimeTableDetail;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.spider.newmodel.CourseRelativeInfo;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/29 22:35
 */
@Slf4j
@Data
public class TimeAndPlace {

    /**
     * 校区名
     */
    private String campusName;
    /**
     * 星期几
     */
    private int classDay;
    /**
     * 节数
     */
    private int classSessions;
    /**
     * 上课周数
     */
    private String classWeek;
    /**
     * 教室名
     */
    private String classroomName;
    /**
     * 持续节数
     */
    private int continuingSession;
    /**
     * 课程名
     */
    @JSONField(name = "coureName")
    private String courseName;
    /**
     * 课程号
     */
    @JSONField(name = "coureNumber")
    private String courseNumber;
    /**
     * 课序号
     */
    @JSONField(name = "coureSequenceNumber")
    private String courseSequenceNumber;
    /**
     * 课程属性名
     */
    private String coursePropertiesName;
    /**
     * 上课教师
     */
    private String courseTeacher;
    /**
     * 执行教育计划编号
     */
    private String executiveEducationPlanNumber;
    /**
     * 示例 2019-2020-1-1170215001201602417000000000001111100000000011
     */
    private String id;
    /**
     *
     */
    private String sksj;
    /**
     * 教学楼
     */
    private String teachingBuildingName;
    /**
     *
     */
    private String time;
    /**
     * 周数描述
     * 示例 11-15周
     */
    private String weekDescription;
    /**
     * 学分
     */
    private String xf;

    public List<CourseTimeTableDetail> convertToCourseTimeTableDetail(CourseRelativeInfo relativeInfo, String attendClassTeacher) {
        List<CourseTimeTableDetail> details = Lists.newArrayList();
        List<int[]> weekList = parseWeek(this.getWeekDescription());
        for (int[] weeks : weekList) {
            CourseTimeTableDetail detail = new CourseTimeTableDetail();
            String[] termYearAndTermOrder = parseTermYearAndTermOrder(relativeInfo.getExecutiveEducationPlanNumber());
            detail.setRoomName(specialProcess(this.getClassroomName(), this.getTeachingBuildingName()));
            detail.setCampusName(this.getCampusName());
            detail.setAttendClassTeacher(attendClassTeacher);
            detail.setContinuingSession(this.getContinuingSession());
            detail.setCourseId(relativeInfo.getCourseNumber());
            detail.setCourseSequenceNumber(this.getCourseSequenceNumber());
            detail.setDay(this.getClassDay());
            detail.setSksj(this.getSksj());
            detail.setWeek(this.getClassWeek());
            detail.setWeekDescription(this.getWeekDescription());
            detail.setOrder(this.getClassSessions());
            detail.setStartWeek(weeks[0]);
            detail.setEndWeek(weeks[1]);
            detail.setTermYear(termYearAndTermOrder[0]);
            detail.setTermOrder(Integer.parseInt(termYearAndTermOrder[1]));
            detail.setDistinct((byte) parseDistinct(this.getClassWeek(), weeks[0], weeks[1]));
            details.add(detail);
        }
        return details;
    }

    private String specialProcess(String classroomName, String teachingBuildingName) {
        if (teachingBuildingName.startsWith(Building.MAIN.getChinese())) {
            return Building.MAIN.getChinese() + classroomName;
        }
        return classroomName;
    }

    private String[] parseTermYearAndTermOrder(String executiveEducationPlanNumber) {
        String[] results = executiveEducationPlanNumber.split("-");
        return new String[]{results[0] + "-" + results[1], results[2]};
    }

    private List<int[]> parseWeek(String weekDescription) {
        String[] weeks = weekDescription.split(",");
        List<int[]> results = Lists.newArrayList();
        String key = "第";
        try {
            if (weeks.length == 1) {
                if (weekDescription.startsWith(key)) {
                    String number = weekDescription.substring(1, weekDescription.length() - 1);
                    int result = Integer.parseInt(number);
                    results.add(new int[]{result, result});
                } else if (weekDescription.length() == 1 && CharUtils.isAsciiNumeric(weekDescription.charAt(0))) {
                    //这是处理3，6-12周这种情况
                    int result = Integer.parseInt(weekDescription);
                    results.add(new int[]{result, result});
                } else {
                    results.add(parseNumber(weekDescription));
                }
                return results;
            }
            for (String str : weeks) {
                results.add(parseNumber(str));
            }
        } catch (NumberFormatException e) {
            log.error ("week parse error weekDescription:{}", weekDescription);
        }
        return results;
    }

    private int[] parseNumber(String origin) {
        String[] strs = origin.split("-");
        if (strs.length == 1) {
            int one = getLastNumberIndex(strs[0]);
            int result = Integer.parseInt(strs[0].substring(0, one));
            return new int[]{result, result};
        }
        int one = Integer.parseInt(strs[0]);
        int two = getLastNumberIndex(strs[1]);
        return new int[]{one, Integer.parseInt(strs[1].substring(0, two))};
    }

    /**
     * 这个方法解析的字符串必须是以数字开头的
     * @param target 目标字符串
     * @return 数字最后的下标
     */
    private int getLastNumberIndex(String target){
        int index = 0;
        for (int i = 0, length = target.length(); i < length; i++) {
            if (!CharUtils.isAsciiNumeric(target.charAt(i))) {
                break;
            }
            index++;
        }
        return index;
    }

    private int parseDistinct(String seq, int start, int end) {
        int count = 0, theoreticalValue = end - start + 1;
        for (int i = start - 1; i < end; i++) {
            if (seq.charAt(i) == '1') {
                count++;
            }
        }
        if (theoreticalValue > count) {
            return start % 2 == 0 ? 2 : 1;
        }
        return 0;
    }
}
