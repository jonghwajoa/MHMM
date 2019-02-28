package xyz.mhmm.commons;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class ErrorResponse {

	private int status;
	private String message;
	private String code;
	private List<FieldError> errors;

	public boolean hasFiledError() {
		return this.errors != null;
	}

	public ErrorResponse(ErrorCode errorCode) {
		this.status = errorCode.getStatus();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}

	public ErrorResponse(ErrorCode errorCode, List<FieldError> errors) {
		this.status = errorCode.getStatus();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.errors = errors;
	}

	@AllArgsConstructor
	@Getter
	public static class FieldError {
		private String field;
		private String value;
		private String reason;
	}

}
