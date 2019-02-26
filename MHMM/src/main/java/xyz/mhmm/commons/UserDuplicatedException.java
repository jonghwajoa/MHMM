package xyz.mhmm.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDuplicatedException extends RuntimeException {
	private String message;

}
