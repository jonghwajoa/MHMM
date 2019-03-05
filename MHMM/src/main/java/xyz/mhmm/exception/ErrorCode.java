package xyz.mhmm.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorCode {

	// Common
	INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"), METHOD_NOT_ALLOWED(405, "C005", " Invalid Input Value"),
	HANDLE_ACCESS_DENIED(403, "C003", "Access is Denied"), NOT_FOUND_EXCEPTION(404, "C004", "Page Not Found"),

	// Auth
	EMAIL_DUPLICATION(400, "A001", "이미 사용중인 이메일 입니다. 다른 이메일을 사용해주세요."),
	ID_DUPLICATION(400, "A001", "이미 사용중인 아이디 입니다. 다른 아이디를 사용해주세요."),
	USER_NOT_EXIST(404, "A003", "아이디 혹은 비밀번호가 올바르지 않습니다.");

	private int status;
	private final String code;
	private final String message;

	ErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.message = message;
		this.code = code;
	}

}