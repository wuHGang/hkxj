package cn.hkxj.platform.pojo;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 课程类型
 * @author junrong.chen
 * @date 2018/9/15
 */
@Slf4j
public enum CourseType {
    /**
     * 未知  可以手动维护
     */
    UNKNOWN(0, "未知"),
	/**
	 * 必修
	 */
    OBLIGATORY(1, "必修"),
	/**
	 * 选修
	 */
    ELECTIVE(2, "选修"),
	/**
	 * 任选
	 */
    OPTIONAL(3, "任选"),
    /**
     * 辅修
     */
    AUXILIARY(4, "辅修");

    Integer code;
	String type;

    CourseType(Integer code, String type) {
		this.code = code;
		this.type = type;
	}

	public static CourseType getCourseByType(String type){
        if (StringUtils.isEmpty(type)) {
            return CourseType.UNKNOWN;
        }
		switch (type){
			case "必修":
				return OBLIGATORY;
			case "选修":
				return ELECTIVE;
			case "任选":
				return OPTIONAL;
            case "辅修":
                return AUXILIARY;
			default:
				log.error("getCourseByType error type:"+type);
				throw new IllegalArgumentException("Invalid type:"+type);
		}
	}

    public static CourseType getCourseByByte(int code) {
		switch (code){
            case 0:
                return UNKNOWN;
            case 1:
				return OBLIGATORY;
            case 2:
				return ELECTIVE;
            case 3:
				return OPTIONAL;
			default:
				log.error("getCourseByByte error type:"+code);
				throw new IllegalArgumentException("Invalid code:"+code);
		}
	}

    public Integer getByte() {
        return this.code;
	}
}
