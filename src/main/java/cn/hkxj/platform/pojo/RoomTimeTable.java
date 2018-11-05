package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author junrong.chen
 * @date 2018/10/30
 */
public class RoomTimeTable {

	private Room room;

	private List<CourseTimeTable> courseTimeTable;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<CourseTimeTable> getCourseTimeTable() {
		return courseTimeTable;
	}

	public void setCourseTimeTable(List<CourseTimeTable> courseTimeTable) {
		if (Objects.isNull(this.courseTimeTable)) {
			this.courseTimeTable = courseTimeTable;
		} else {
			this.courseTimeTable.addAll(courseTimeTable);
		}
	}

	public void setCourseTimeTable(CourseTimeTable courseTimeTable) {
		if (Objects.isNull(this.courseTimeTable)) {
			this.courseTimeTable = new ArrayList<>();
		}
			this.courseTimeTable.add(courseTimeTable);
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
