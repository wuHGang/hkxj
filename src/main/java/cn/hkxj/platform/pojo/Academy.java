package cn.hkxj.platform.pojo;

/**
 * @author junrong.chen
 * @date 2018/10/1
 */
public enum Academy {
	/**
	 *
	 */
	Environment((byte) 1, "环境与化工学院"),
	Safe((byte) 2, "安全工程学院"),
	Electrical ((byte) 3, "电气与控制工程学院"),
	Electronics((byte) 4, "电子与信息工程学院"),
	Mechanical((byte) 5, "机械工程学院"),
	Economy((byte) 6, "经济学院"),
	Management((byte) 7, "管理学院"),
	Humanities((byte) 8, "人文与社会科学学院"),
	Marx((byte) 9, "马克思主义学院"),
	Computer((byte) 10, "计算机与信息工程学院"),
	Material((byte) 11, "材料科学与工程学院"),
	ForeignLanguage((byte) 12, "外国语学院"),
	Building((byte) 13, "国际教育学院"),
	Mining((byte) 14, "矿业工程学院");

	byte code;
	String name;
	Academy(byte code, String name) {
		this.code = code;
		this.name = name;
	}
}
