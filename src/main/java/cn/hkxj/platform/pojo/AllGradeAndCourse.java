package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junrong.chen
 */
public class AllGradeAndCourse {
	private List<List<GradeAndCourse>> list = new ArrayList<>();

	public void addGradeAndCourse(List<GradeAndCourse> gradeAndCourse){
		list.add(gradeAndCourse);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("list", list)
				.toString();
	}

	/**
	 * 获取当前学期的成绩
	 * @return 当前学期成绩的列表
	 */
	public List<GradeAndCourse> getCurrentTermGrade(){
		return list.get(list.size() -1);
	}

}
