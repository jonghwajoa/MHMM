package xyz.mhmm.chatRoom.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;

@SuppressWarnings("serial")
public class OneToOneNotFoundException extends BusinessException {

	public OneToOneNotFoundException() {
		super(ErrorCode.ONETOONE_NOT_FOUND);
	}

}
