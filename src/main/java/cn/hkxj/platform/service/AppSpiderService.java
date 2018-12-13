package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.ExamTimeTable;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.spider.AppSpider;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import com.google.common.base.Splitter;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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


    public static void main(String[] args) {
//        String[] data = StreamSupport.stream(DATA_SPLITTER.split(times[2]).spliterator(), false).toArray(String[]::new);
    }
}
