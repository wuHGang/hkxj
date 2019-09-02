package cn.hkxj.platform.pojo.constant;

/**
 * @author junrong.chen
 * @date 2018/10/28
 */
public enum Direction {
	/**
	 *
	 */
	CORRECT(0),
	N(1),
	S(2),
	E(3),
	W(4),
	EN(5),
	ES(6),
	WN(7),
	WS(8);

	private int code;

	Direction(int code) {
		this.code = code;
	}

	public static Direction getDirectionByCode(int code){
		switch (code){
			case 0:
				return CORRECT;
			case 1:
				return N;
			case 2:
				return S;
			case 3:
				return E;
			case 4:
				return W;
			case 5:
				return EN;
			case 6:
				return ES;
			case 7:
				return WN;
			case 8:
				return WS;
			default:
				throw new IllegalArgumentException("no code match");
		}

	}

	public static Direction getDirectionObjectByDirection(String direction){
		switch (direction){
			case "":
				return CORRECT;
			case "N":
				return N;
			case "S":
				return S;
			case "E":
				return E;
			case "W":
				return W;
			case "EN":
				return EN;
			case "ES":
				return ES;
			case "WN":
				return WN;
			case "WS":
				return WS;
			default:
				throw new IllegalArgumentException("no code match");
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
