package xyz.mhmm.messenge.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;

@SuppressWarnings("serial")
public class NotAccessException extends BusinessException {

	public NotAccessException() {
		super(ErrorCode.CHATROOM_NOT_ACCESS);
	}
}
