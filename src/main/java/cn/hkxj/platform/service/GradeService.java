package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.GradeDTO;
import cn.hkxj.platform.pojo.Wechatuser;

import java.io.IOException;
import java.util.List;

/**
 * @author Yuki
 * @date 2018/7/14 12:20
 */
public interface GradeService {

	/**
	 * 通过用户的班级名获取对应的成绩列表，如果数据库中没有对应用户的成绩列表
	 * 就先通过爬虫爬取相应的数据，然后插入到数据库中。
	 *
	 * @param wechatuser
	 * @return
	 * @throws IOException
	 */
	List<GradeDTO> getGradesByClassname(Wechatuser wechatuser) throws IOException;

	/**
	 * 将包含有成绩的实体列表转换成一段文本
	 *
	 * @param gradeDTOS
	 * @return
	 */
	String toText(List<GradeDTO> gradeDTOS);
}
