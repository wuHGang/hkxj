package cn.hkxj.platform.service.impl;

import cn.hkxj.platform.mapper.WechatExamMapper;
import cn.hkxj.platform.pojo.Exam;
import cn.hkxj.platform.pojo.Wechatuser;
import cn.hkxj.platform.service.BaseService;
import cn.hkxj.platform.service.ExamService;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author flattery
 */
@Service
public class ExamServiceImpl extends BaseService implements ExamService {

	//    @Autowired
	private WechatExamMapper examMapper;

	public ExamServiceImpl(@Autowired WechatExamMapper examMapper) {
		this.examMapper = examMapper;
	}

	private List<Exam> getExamList(List<LinkedTreeMap> list) throws IOException {
		List<Exam> examList = new ArrayList<>();
		for (LinkedTreeMap<Object, Object> val : list) {
			Exam exam = new Exam();
			for (Map.Entry entry : val.entrySet()) {
				String key = (String) entry.getKey();
				try {
					switch (key) {
						case "ksdd": {//考试地点
							String originalStr = (String) entry.getValue();
							String[] strs = originalStr.split("\\*");
							StringBuffer buffer = new StringBuffer();
							for (String str : strs) {
								buffer.append(str);
							}
							exam.setClassRoom(new String(buffer.toString().getBytes(), "UTF-8"));
							break;
						}
						case "time": {//将time返回的字符串，拆分成week,day,specificTime三个字段
							String str = (String) entry.getValue();
							String originalStr = (String) entry.getValue();
							String[] strs = originalStr.split("\\*");
							exam.setExamWeek(strs[0]);
							exam.setExamDay(strs[1]);
							exam.setSpecificTime(strs[2]);
							break;
						}
						case "xn": {//年份
							String str = (String) entry.getValue();
							exam.setXn(new String(str.getBytes(), "UTF-8"));
							break;
						}
						case "kcmc": {//课程名称
							String str = (String) entry.getValue();
							exam.setExamName(new String(str.getBytes(), "UTF-8"));
							break;
						}
						default:
							break;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			examList.add(exam);
		}
		return examList;
	}

	/**
	 * 向数据库中插入相应的考试安排
	 * map中应该包含两个键值对
	 * 第一个键值对，键为examList,值为一个包含考试安排信息的List对象
	 * 第二个键值对，键位classname,值为用户对应的班级名
	 *
	 * @param params
	 */
	private void insertExam(Map<String, Object> params) {
		examMapper.insertExam(params);
	}


	/**
	 * 根据Classname查找对应的用户的考试安排
	 *
	 * @param wechatuser
	 * @return list
	 */
	public List<Exam> getListByClassname(Wechatuser wechatuser) throws IOException {

		List<Exam> list = examMapper.getExamByClassname(wechatuser.getClassname());
		System.out.println(list);
		if (list == null || list.size() == 0) {
			list = getExamList(getAppsSider(wechatuser).getExam());
			Map<String, Object> params = new HashMap<>();
			params.put("examList", list);
			params.put("classname", wechatuser.getClassname());
			System.out.println(list.size());
			examMapper.insertExam(params);
		}
		return list;
	}

	public String toText(List<Exam> list) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("考试安排如下:\n");
		list.forEach(exam -> {
			buffer.append("课程名称:").append(exam.getExamName()).append("\n").
					append("考试地点:").append(exam.getClassname()).append("\n").
					append("考试时间:").append(exam.getExamWeek()).append(" \n").append(exam.getExamDay()).append(" \n")
					.append(exam.getSpecificTime()).append("\n");
		});
		return buffer.toString();
	}


}
