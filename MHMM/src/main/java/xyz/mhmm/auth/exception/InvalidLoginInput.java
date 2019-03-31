package xyz.mhmm.auth.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.utils.ErrorCode;

@SuppressWarnings("serial")
public class InvalidLoginInput extends BusinessException {

	public InvalidLoginInput() {
		super(ErrorCode.INVALID_LOGIN_INPUT);
	}
}
