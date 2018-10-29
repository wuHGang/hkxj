package cn.hkxj.platform.pojo;

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
				throw new IllegalArgumentException("no building match");
		}
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

}
