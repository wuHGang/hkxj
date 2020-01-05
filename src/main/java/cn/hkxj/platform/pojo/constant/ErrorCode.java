package cn.hkxj.platform.pojo.constant;

/**
 * @author junrong.chen
 * @date 2018/10/10
 */
public enum  ErrorCode {
	/**
	 * 客户端错误
	 */
	NO_DATA(400),
	USER_UNAUTHORIZED(401),
	ACCOUNT_OR_PASSWORD_INVALID(402),
	OPENID_EXIST(403),
	VERIFY_CODE_ERROR(404),
	/**
	 * 无法登录，通常是输入密码次数过多
	 */
	LOGIN_ERROR(405),

	/**
	 * 未完成评估，需要手动到教务网上完成评估
	 */
	Evaluation_ERROR(406),

	/**
	 * 教务网的异常，通常网络波动等原因，提醒用户重试即可
	 */
	URP_EXCEPTION(407),

	/**
	 *服务器端错误
	 */
	SYSTEM_ERROR(500),
	READ_TIMEOUT(504);

	private int errorCode;

	ErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
