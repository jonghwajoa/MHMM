package xyz.mhmm.auth.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.utils.ErrorCode;

@SuppressWarnings("serial")
public class EmailDuplicatedException extends BusinessException {

	public EmailDuplicatedException() {
		super(ErrorCode.EMAIL_DUPLICATION);
	}

}
