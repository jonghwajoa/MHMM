package xyz.mhmm.friend.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.utils.ErrorCode;

@SuppressWarnings("serial")
public class AlreadyFriendException extends BusinessException {

	public AlreadyFriendException() {
		super(ErrorCode.USER_ALREADY_FRIEND);
	}
}
