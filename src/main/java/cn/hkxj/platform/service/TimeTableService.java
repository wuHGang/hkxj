package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.CourseTimeTableMapper;
import cn.hkxj.platform.pojo.example.CourseTimeTableExample;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/10/31
 */
@Service("timeTableService")
@Slf4j
public class TimeTableService {
	@Resource
	private CourseTimeTableMapper courseTimeTableMapper;

	public List<CourseTimeTable> getTimeTableFromDB(int schoolWeek){

		CourseTimeTableExample courseTimeTableExample = new CourseTimeTableExample();
		courseTimeTableExample.createCriteria()
				.andWeekEqualTo(SchoolTimeUtil.getDayOfWeek())
				.andYearEqualTo(2019)
				.andTermEqualTo(2)
				.andDistinctIn(Arrays.asList(0, SchoolTimeUtil.getWeekDistinct()))
				.andStartLessThanOrEqualTo(schoolWeek)
				.andEndGreaterThanOrEqualTo(schoolWeek);
		return courseTimeTableMapper.selectByExample(courseTimeTableExample);
	}

	public List<CourseTimeTable> getTimeTableFromDB(int schoolWeek,int dayOfWeek){

		CourseTimeTableExample courseTimeTableExample = new CourseTimeTableExample();
		courseTimeTableExample.createCriteria()
				.andWeekEqualTo(dayOfWeek)
				.andYearEqualTo(2019)
				.andTermEqualTo(2)
				.andDistinctIn(Arrays.asList(0, SchoolTimeUtil.getWeekDistinct()))
				.andStartLessThanOrEqualTo(schoolWeek)
				.andEndGreaterThanOrEqualTo(schoolWeek);
		return courseTimeTableMapper.selectByExample(courseTimeTableExample);
	}
}
