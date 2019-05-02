package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

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
        return list.size() == 0 ? Lists.newArrayList() : list.get(list.size() - 1);
	}

	public List<GradeAndCourse> getEverTermGrade(){
		List<GradeAndCourse> everList=new ArrayList<>();
		for (List<GradeAndCourse> o:list){
			everList.addAll(o);
		}
		return everList;
	}

}
