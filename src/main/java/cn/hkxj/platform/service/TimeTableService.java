package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.CourseTimeTableMapper;
import cn.hkxj.platform.pojo.CourseTimeTable;
import cn.hkxj.platform.pojo.CourseTimeTableExample;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import com.google.common.collect.HashMultimap;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
				.andStartLessThanOrEqualTo(schoolWeek)
				.andEndGreaterThanOrEqualTo(schoolWeek);
		return courseTimeTableMapper.selectByExample(courseTimeTableExample);
	}

}
