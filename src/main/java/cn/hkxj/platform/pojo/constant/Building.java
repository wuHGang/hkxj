package cn.hkxj.platform.pojo.constant;

import com.google.common.base.MoreObjects;

/**
 * @author junrong.chen
 * @date 2018/10/28
 */
public enum Building {
	/**
	 *
	 */
	MAIN("主楼", -1),
	MAIN_EAST("主楼(东楼)", 1),
	MAIN_WEST("主楼(西楼)", 3),
	SCIENCE_ALL("科大", 2),
	SCIENCE("科厦", -1),
	SCIENCE_HIGH("科高", -1),
	PLAYGROUND("操场", 4),
	LABORATORY("实验室", -1),
	/**
	 * 计算机前楼，建工学院机房 etc
	 */
	LABORATORY_BUILDING("实验楼", 5),
	LIBRARY("图书馆", 6);

	private String chinese;

	private int urpCode;

	Building(String chinese, int urpCode) {
		this.chinese = chinese;
		this.urpCode = urpCode;
	}

	public static Building getBuildingByName(String name){
		for (Building building : Building.values()) {
			if(building.getChinese().equals(name)){
				return building;
			}
		}
		throw new IllegalArgumentException("no building match:" + name);
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
			case "图书馆":
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
