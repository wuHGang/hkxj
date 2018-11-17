package cn.hkxj.platform.exceptions;

import java.io.IOException;

public class ReadTimeoutException extends RuntimeException {
	public ReadTimeoutException(String description) {
		super(description);
	}

	public ReadTimeoutException(String description, IOException e) {
		super(description, e);
	}
}
