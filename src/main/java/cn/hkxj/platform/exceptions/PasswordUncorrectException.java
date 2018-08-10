package cn.hkxj.platform.exceptions;

public class PasswordUncorrectException extends Exception {
	public PasswordUncorrectException() {
		super();
	}

	public PasswordUncorrectException(String description) {
		super(description);
	}

	public PasswordUncorrectException(String description, Exception e) {
		super(description, e);
	}
}
