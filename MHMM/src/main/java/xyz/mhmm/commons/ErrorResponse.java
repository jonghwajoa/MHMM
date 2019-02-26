package xyz.mhmm.commons;

import java.util.LinkedList;
import java.util.List;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private List<FieldError> errors = new LinkedList<>();

	public void add(String field, String reason) {
		errors.add(new ErrorResponse.FieldError(field, reason));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		errors.forEach(e -> sb.append("Filed: " + e.field + ", Reason: " + e.reason + "\n"));
		return sb.toString();
	}

	@AllArgsConstructor
	@Getter
	public static class FieldError {
		private String field;
		private String reason;

		@Override
		public String toString() {
			return "Filed: " + this.field + ", Reason: " + this.reason;
		}
	}

}
