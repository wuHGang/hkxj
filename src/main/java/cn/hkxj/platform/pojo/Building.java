package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author junrong.chen
 * @date 2018/10/28
 */
public enum Building {
	/**
	 *
	 */
	MAIN("主楼"),
	SCIENCE("科厦"),
	SCIENCE_HIGH("科高"),
	PLAYGROUND("操场"),
	LABORATORY("实验室");

	private String chinese;

	Building(String chinese) {
		this.chinese = chinese;
	}

	public static Building getBuildingByName(String name){
		switch (name){
			case "主楼":
				return MAIN;
			case "科厦":
				return SCIENCE;
			case "科高":
				return SCIENCE_HIGH;
			case "操场":
				return PLAYGROUND;
			case "实验室":
				return LABORATORY;
			default:
				throw new IllegalArgumentException("no building match:" + name);
		}
	}

	public static boolean isExist(String name){
		switch (name){
			case "主楼":
				return true;
			case "科厦":
				return true;
			case "科高":
				return true;
			case "操场":
				return true;
			case "实验室":
				return true;
			default:
				return false;
		}
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("chinese", chinese)
				.toString();
	}
}
