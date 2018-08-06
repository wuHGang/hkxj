package cn.hkxj.platform.service.wechat.common.lesson;

import cn.hkxj.platform.pojo.Lesson;
import cn.hkxj.platform.pojo.Wechatuser;

import java.io.IOException;
import java.util.List;

/**
 * @author Yuki
 * @date 2018/7/14 11:49
 */
public interface LessonService {

	/**
	 * 通过用户的班级名获取对应的课程列表，如果数据库中没有对应班级的课程列表
	 * 就先通过爬虫爬取相应的数据，然后插入到数据库中。
	 *
	 * @param wechatuser
	 * @return
	 * @throws IOException
	 */
	List<Lesson> getLessonsByClassname(Wechatuser wechatuser) throws IOException;

	/**
	 * 将包含有课程信息的实体列表转换成一段文本
	 *
	 * @param lessons
	 * @return
	 */
	String toText(List<Lesson> lessons);
}
