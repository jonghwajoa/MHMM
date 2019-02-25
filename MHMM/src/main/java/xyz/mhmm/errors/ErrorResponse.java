package xyz.mhmm.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse extends RuntimeException{
	private String message;
	private String code;
	
}
