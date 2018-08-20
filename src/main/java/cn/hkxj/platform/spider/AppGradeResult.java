package cn.hkxj.platform.spider;

import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Grade;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于解析请求grade以后得到的结果
 */
@Slf4j
class AppGradeResult {
	private ArrayList result;
	private List<Course> courses;
	private List<Grade> grades;

	AppGradeResult(ArrayList result) {
		this.result = result;
	}

	private void prase(ArrayList<Map> result){
		for(Map item: result){
			ArrayList<Map> items = (ArrayList)item.get("items");
			Object year = item.get("xn");
			Object term = item.get("xq");
			for(Map detail: items) {

				String uid = detail.get("kcdm").toString();
				String type = detail.get("kcxz").toString();
				String name = detail.get("kcmc").toString();
				Float score = (Float)detail.get("cj");
				Float credit = (Float)detail.get("xf");

			}
		}
	}

	private List getCurrentGrade(){
		return null;
	}

//	private Grade setGrade(Map detail) {
//		Grade grade = new Grade();
//		return null;
//	}
//
//	private Course setCourse(Map detail) {
//
//		Course course = new Course();
//		course.setCredit(credit);
//		course.setName(name);
//		course.setType((byte) 0);
//		course.setUid(uid);
//		return course;
//	}
//
//	Course getCourse() {
//		return null;
//	}
//
//	Grade getGrade() {
//		return null;
//	}
}
