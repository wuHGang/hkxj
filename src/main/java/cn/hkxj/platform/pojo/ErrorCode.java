package cn.hkxj.platform.pojo;

/**
 * @author junrong.chen
 * @date 2018/10/10
 */
public enum  ErrorCode {
	/**
	 * 客户端错误
	 */
	USER_UNAUTHORIZED(401),
	ACCOUNT_OR_PASSWORD_INVALID(402);

	ErrorCode(int i) {

	}
}
