package cn.hkxj.platform.pojo;

import lombok.extern.slf4j.Slf4j;

/**
 * 课程类型
 * @author junrong.chen
 * @date 2018/9/15
 */
@Slf4j
public enum CourseType {
	/**
	 * 必修
	 */
	OBLIGATORY((byte) 1, "必修"),
	/**
	 * 选修
	 */
	ELECTIVE((byte) 2, "选修"),
	/**
	 * 任选
	 */
	OPTIONAL((byte) 3, "任选");

	byte code;
	String type;

	CourseType(byte code, String type) {
		this.code = code;
		this.type = type;
	}

	public static CourseType getCourseByType(String type){
		switch (type){
			case "必修":
				return OBLIGATORY;
			case "选修":
				return ELECTIVE;
			case "任选":
				return OPTIONAL;
			default:
				log.error("getCourseByType error type:"+type);
				throw new IllegalArgumentException("Invalid type:"+type);
		}
	}

	public static CourseType getCourseByByte(byte code){
		switch (code){
			case (byte) 1:
				return OBLIGATORY;
			case (byte) 2:
				return ELECTIVE;
			case (byte) 3:
				return OPTIONAL;
			default:
				log.error("getCourseByByte error type:"+code);
				throw new IllegalArgumentException("Invalid code:"+code);
		}
	}

	public byte getByte(){
		return (byte)this.code;
	}
}
