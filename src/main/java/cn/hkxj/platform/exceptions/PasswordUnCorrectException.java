package cn.hkxj.platform.exceptions;

public class PasswordUnCorrectException extends RuntimeException {
	public PasswordUnCorrectException() {
		super();
	}

	public PasswordUnCorrectException(String description) {
		super(description);
	}

}
