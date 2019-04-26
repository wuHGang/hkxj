package cn.hkxj.platform.pojo.timetable;

import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import lombok.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author junrong.chen
 * @date 2018/10/30
 */
public class RoomTimeTable {

	private Room room;

	private List<CourseTimeTable> courseTimeTable;

	private Set<Integer> emptyOrder;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<CourseTimeTable> getCourseTimeTable() {
		return courseTimeTable;
	}

	public void setCourseTimeTable(@NonNull List<CourseTimeTable> courseTimeTable) {
		this.courseTimeTable = courseTimeTable;
	}

	/**
	 * 获取该教室当天没课的节次的集合
	 * @return
	 */
	public Set<Integer> getEmptyOrder(){
		if (!Objects.isNull(emptyOrder)){
			return emptyOrder;
		}
		HashSet<Integer> orderSet = Sets.newHashSet(1,3,5,7,9);
		for (CourseTimeTable timeTable : courseTimeTable) {
			orderSet.remove(timeTable.getOrder());
		}
		emptyOrder = orderSet;
		return orderSet;
	}

	public boolean isEmptyByOrder(int order){
		return getEmptyOrder().contains(order);
	}


	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("room", room)
				.add("courseTimeTable", courseTimeTable)
				.toString();
	}

	public String toText() {
		StringBuilder builder = new StringBuilder(room.getName()).append(":");
		if (Objects.isNull(courseTimeTable) || courseTimeTable.size() == 0){
			builder.append("今天没课");
		}
		else {
			for (CourseTimeTable table : courseTimeTable) {
				builder.append("第").append(table.getOrder()).append("节有课 ");
			}
		}
		return new String(builder);
	}

}
