package cn.hkxj.platform.exceptions;

public class InvalidTokenException extends Exception {
	public InvalidTokenException(String description) {
		super(description);
	}
}
