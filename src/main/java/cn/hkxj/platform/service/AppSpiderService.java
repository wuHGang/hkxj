package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.CourseTimeTableMapper;
import cn.hkxj.platform.mapper.RoomMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.AppSpider;
import cn.hkxj.platform.spider.UrpCourseSpider;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import com.google.common.base.Splitter;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

@Service("appSpiderService")
public class AppSpiderService {
	@Resource
	private RoomService roomService;
	@Resource
	private CourseMapper courseMapper;
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private CourseTimeTableMapper courseTimeTableMapper;
	private static Splitter SPLITTER = Splitter.on('*').trimResults().omitEmptyStrings();
    private static Splitter DATA_SPLITTER = Splitter.on('-').trimResults().omitEmptyStrings();
    private static Splitter TIME_SPLITTER = Splitter.on(':').trimResults().omitEmptyStrings();
	private static Pattern FIND_NUM = Pattern.compile("[^0-9]");


    public ArrayList<ExamTimeTable> getExamByAccount(int account) throws PasswordUncorrectException {
        AppSpider appSpider = new AppSpider(account);
        appSpider.getToken();
        ArrayList<ExamTimeTable> examTimeTableArrayList = new ArrayList<>();
        for (Object o : appSpider.getExam()) {
            ExamTimeTable examTimeTable = new ExamTimeTable();
            Map item = (Map) o;
            examTimeTable.setYear(xnToYear((String) item.get("xn")));
            examTimeTable.setTerm(Integer.parseInt((String)item.get("xq")));
            examTimeTable.setCourse(parseCourseText((String)item.get("kcmc")));
            examTimeTable.setRoom(parseRoomText((String) item.get("ksdd")));
            parseExamTime((String) item.get("time"), examTimeTable);
            examTimeTableArrayList.add(examTimeTable);
        }
        return examTimeTableArrayList;
    }

    public AllGradeAndCourse getGradeAndCourseByAccount(int account) throws PasswordUncorrectException {
        AppSpider appSpider = new AppSpider(account);
        appSpider.getToken();
        return appSpider.getGradeAndCourse();
    }


    /**
     * 将文本时间解析为时间对象
     * @param time 文本  第20周*星期2*10:20-11:50
     * @param examTimeTable 考试时间对象
     */
    private ExamTimeTable parseExamTime(String time, ExamTimeTable examTimeTable) {
        String[] times = StreamSupport.stream(SPLITTER.split(time).spliterator(), false).toArray(String[]::new);
        Matcher schoolWeekMatcher = FIND_NUM.matcher(times[0]);
        Matcher weekMatcher = FIND_NUM.matcher(times[1]);

        int schoolWeek = Integer.parseInt(schoolWeekMatcher.replaceAll(""));
        int week = Integer.parseInt(weekMatcher.replaceAll(""));

        examTimeTable.setSchoolWeek(schoolWeek);
        examTimeTable.setWeek(week);

        String[] data = StreamSupport.stream(DATA_SPLITTER.split(times[2]).spliterator(), false).toArray(String[]::new);

        String[] start = StreamSupport.stream(TIME_SPLITTER.split(data[0]).spliterator(), false).toArray(String[]::new);
        String[] end = StreamSupport.stream(TIME_SPLITTER.split(data[1]).spliterator(), false).toArray(String[]::new);

        DateTime startTime = SchoolTimeUtil.getDateBySchoolTime(schoolWeek, week)
                .withHourOfDay(Integer.parseInt(start[0]))
                .withMinuteOfHour(Integer.parseInt(start[1]));

        DateTime endTime = SchoolTimeUtil.getDateBySchoolTime(schoolWeek, week)
                .withHourOfDay(Integer.parseInt(end[0]))
                .withMinuteOfHour(Integer.parseInt(end[1]));

        examTimeTable.setStart(startTime.toDate());
        examTimeTable.setEnd(endTime.toDate());

		return examTimeTable;
    }

    private Course parseCourseText(String courseText) {
        List<Course> courses = courseMapper.selectCourseByName(courseText);
        if (courses.size() == 0) {
            throw new IllegalArgumentException(courseText);
        }
        return courses.get(0);
    }

    /**
     *
     * @param roomText 主楼（西楼）*WS0202 科大*科N206
     * @return
     */
    private Room parseRoomText(String roomText) {
        // TODO  有些教室是在实验楼 现在还无法将其解析为对应对象
        String roomName = StreamSupport.stream(SPLITTER.split(roomText).spliterator(), false).toArray(String[]::new)[1];
        if (roomName.startsWith("科")) {
            return roomService.getRoomByName(roomName);
        }
        if (roomName.length() > 4){
            return roomService.getRoomByName("主楼"+roomName);
        }
        throw new IllegalArgumentException("无法解析考试教室: "+roomName);
    }

	private static int xnToYear(String xn) {
		String[] split = xn.split("-");
		return Integer.parseInt(split[0]);
	}

    /**
     * 从m黑科技上获取学生的课表信息，转换成courseTimeTable形式并存入数据库
     * @param student
     * @return
     */
    public void getLessonFromApp(Student student) {
        AppSpider appSpider = new AppSpider(student.getAccount());
        appSpider.getToken();
        List lesson = appSpider.getLesson();
        //lesson包含了该学生本学期的所有课程
        for (Object o : lesson) {
            Map lessonMap=(Map)o;

            String startToEnd = (String) lessonMap.get("qsz");

            //获取课程上课时间
            char orderChar = startToEnd.charAt(startToEnd.indexOf("{") + 1);

            //获取课程星期
            char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};
            char weekChar = startToEnd.charAt(startToEnd.indexOf("期") + 1);
            int week = 0;
            for (int j = 0; j < cnArr.length; j++) {
                if (weekChar == cnArr[j]) {
                    week = j + 1;
                    break;
                }
            }

            //获取课程起始周和结束周，并判断单双周情况
            //课程存在情况：
            //a-b 连续上课
            //a-b,c 连续上课，隔若干周有加课
            //a-b,c-d 分段连续上课
            //a,b,c 单双周上课
            startToEnd = startToEnd.substring(0, startToEnd.indexOf("周"));
            int start ;
            int end ;
            int distinct = 0;
            if (startToEnd.indexOf("-")!=-1) {
                String[] startAndEnd ;
                if (startToEnd.indexOf(",")!=-1){
                    startAndEnd = startToEnd.split("[, -]");
                    start = Integer.valueOf(startAndEnd[0]);
                    end = Integer.valueOf(startAndEnd[startAndEnd.length-1]);
                }else {
                    startAndEnd = startToEnd.split("-");
                    start = Integer.valueOf(startAndEnd[0]);
                    end = Integer.valueOf(startAndEnd[1]);
                }
            } else {
                String[] startAndEnd = startToEnd.split(",");
                start = Integer.valueOf(startAndEnd[0]);
                end = Integer.valueOf(startAndEnd[startAndEnd.length - 1]);
                if (start % 2 == 0) {
                    distinct = 2;
                } else {
                    distinct = 1;
                }
            }
            //获取课程信息
            Course course = new Course();
            String bid=(String) lessonMap.get("bid");
            Map sc=appSpider.getSchedule(String.valueOf(end));
            List slist=(List) sc.get("wlist");
            for(Object objSlist : slist){
                Map courseMap=(Map)objSlist;
                String compareBid=(String)courseMap.get("bid");
                if (bid.equals(compareBid)){
                    course.setUid((String)((Map) objSlist).get("kcdm"));
                    course.setName((String)courseMap.get("kcmc"));
                    CourseType courseType=CourseType.getCourseByByte(0);
                    course.setType(courseType);
                    course.setCredit(0);
                }
            }

            if (!courseMapper.ifExistCourse(course.getUid())) {
                UrpCourseSpider urpCourseSpider = new UrpCourseSpider(student.getAccount(), student.getPassword());
                course.setAcademy(urpCourseSpider.getAcademyId(course.getUid()));
                if(course.getName()==null){
                    course.setName(urpCourseSpider.getCourseName(course.getUid()));
                }
                System.out.println(course);
                courseMapper.insert(course);
            }

            course=courseMapper.selectNameByUid(course.getUid()).get(0);

            //获取课程的教室信息
            String roomName = (String) lessonMap.get("jxdd");
            Room room;
            if(roomName.equals("操场1")){
                room = roomMapper.selectByFuzzy(roomName);
            }
            else room = roomMapper.selectByFuzzy("%" + roomName);
            if(room!=null){
                CourseTimeTable courseTimeTable = new CourseTimeTable();
                courseTimeTable.setCourse(course);
                courseTimeTable.setYear(2019);
                courseTimeTable.setTerm(2);
                courseTimeTable.setStart(start);
                courseTimeTable.setEnd(end);
                courseTimeTable.setWeek(week);
                courseTimeTable.setOrder(Integer.parseInt("" + orderChar));
                courseTimeTable.setDistinct(distinct);
                courseTimeTable.setRoom(room);
                if(courseTimeTableMapper.isCourseTimeTableExist(courseTimeTable) == null){
                    courseTimeTableMapper.insert(courseTimeTable);
                }
            }
        }
    }

    }
