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

	INVALID_LOGIN_INPUT(404, "A003", "아이디 혹은 비밀번호가 올바르지 않습니다."),

	// friend
	USER_NOT_FOUND(404, "F001", "검색한 아이디는 존재하지 않습니다."), USER_ALREADY_FRIEND(400, "F002", "이미 친구등록한 유저입니다."),
	OWN_SELF_ADD_FRIEND(400, "F003", "자기자신을 친구추가 할 수 없습니다."), FRIEND_SERACH_NOT_FOUND(404, "F004", "존재하지 않는 유저 입니다."),

	// OneToOne ChatRoom
	ONETOONE_NOT_FOUND(404, "O002", "존재하지 않는 채팅방 입니다."),

	// Message
	CHATROOM_NOT_ACCESS(403, "M001", "접근할 수 없는 채팅방입니다."),
	CHATROOM_NOT_FOUND(404, "M002", "접근할 수 없는 채팅방입니다.")
	

	;

	private int status;
	private final String code;
	private final String message;

	ErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.message = message;
		this.code = code;
	}

}