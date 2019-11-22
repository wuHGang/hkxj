package cn.hkxj.platform.pojo;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author flattery
 */
@Data
@Accessors(chain = true)
public class Exam {

	private Course course;

	private UrpClassroom classRoom;    //考试地点

	/**
	 * 考试名称
	 */
	private String examName;

	private String examWeekOfTerm;    //考试第几周

	private String examDay;     //星期几

	private String startTime;

	private String endTime;

	/**
	 * 考试日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
}
