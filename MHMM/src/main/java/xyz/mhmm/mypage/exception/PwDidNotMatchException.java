package xyz.mhmm.mypage.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.utils.ErrorCode;

@SuppressWarnings("serial")
public class PwDidNotMatchException extends BusinessException {

	public PwDidNotMatchException() {
		super(ErrorCode.PASSWORD_DID_NOT_MATCH);
	}

}
