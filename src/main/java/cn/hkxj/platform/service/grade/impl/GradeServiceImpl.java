package cn.hkxj.platform.service.grade.impl;

import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.GradeDTO;
import cn.hkxj.platform.pojo.Wechatuser;
import cn.hkxj.platform.service.base.BaseService;
import cn.hkxj.platform.service.grade.GradeService;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Yuki
 * @date 2018/7/14 12:22
 */
@Service
public class GradeServiceImpl extends BaseService implements GradeService {

	/**
	 * 将通过爬虫获得的成绩列表进行封装
	 *
	 * @param list
	 * @return
	 */
	private List<GradeDTO> getGradeDTOList(List<LinkedTreeMap> list) {
		List<GradeDTO> gradeDTOS = new ArrayList<>();
		for (LinkedTreeMap<Object, Object> val : list) {
			GradeDTO gradeDTO = new GradeDTO();
			for (Map.Entry entry : val.entrySet()) {
				String key = (String) entry.getKey();
				try {
					switch (key) {
						case "xn": {//学年
							String xn = (String) entry.getValue();
							gradeDTO.setXn(new String(xn.getBytes(), "UTF-8"));
							break;
						}
						case "xq": {//学期
							String str = (String) entry.getValue();
							gradeDTO.setXq(new String(str.getBytes(), "UTF-8"));
							break;
						}
						case "items": {//成绩列表
							List<LinkedTreeMap> gradeList = (List<LinkedTreeMap>) entry.getValue();
							gradeDTO.setGradeList(getGradeList(gradeList));
							break;
						}
						default:
							break;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			gradeDTOS.add(gradeDTO);
		}
		return gradeDTOS;
	}

	/**
	 * 因为每个学期的成绩是封装在一个List中，所以使用一个方法进行解析
	 *
	 * @param gradeList
	 * @return
	 */
	private List<Grade> getGradeList(List<LinkedTreeMap> gradeList) {
		List<Grade> grades = new ArrayList<>();
//		for (LinkedTreeMap<Object, Object> val : gradeList) {
//			Grade grade = new Grade();
//			for (Map.Entry entry : val.entrySet()) {
//				String key = (String) entry.getKey();
//				try {
//					switch (key) {
//						case "kcxz": {//课程类型
//							String type = (String) entry.getValue();
//							grade.setType(new String(type.getBytes(), "UTF-8"));
//							break;
//						}
//						case "cj": {//成绩
//							String str = (String) entry.getValue();
//							grade.setCj(Double.parseDouble(str));
//							break;
//						}
//						case "jd": {//绩点
//							Double jd = (Double) entry.getValue();
//							grade.setJd(jd);
//							break;
//						}
//						case "kcmc": {//课程名称
//							String str = (String) entry.getValue();
//							grade.setCourseName(new String(str.getBytes(), "UTF-8"));
//							break;
//						}
//						case "xf": {//学分
//							Double str = (Double) entry.getValue();
//							grade.setXf(str);
//							break;
//						}
//						default:
//							break;
//					}
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}
//			}
//			grades.add(grade);
//		}
		return grades;
	}

	@Override
	public List<GradeDTO> getGradesByClassname(Wechatuser wechatuser) throws IOException {
		return getGradeDTOList(getAppsSider(wechatuser).getGrade());
	}

	public String toText(List<GradeDTO> gradeDTOS) {
		StringBuffer buffer = new StringBuffer();
//		gradeDTOS.forEach(gradeDTO -> {
//			if (gradeDTO.getXn().equals("2017-2018") && gradeDTO.getXq().equals("2")) {
//				buffer.append("学年").append(gradeDTO.getXn()).append("第").append(gradeDTO.getXq()).append("学期\n");
//				gradeDTO.getGradeList().forEach(grade -> {
//					buffer.append("课程名称").append(grade.getCourseName()).append("\n");
//					buffer.append("成绩").append(grade.getCj())
//							.append("绩点").append(grade.getJd());
//				});
//			}
//
//		});
		return buffer.toString();
	}
}
