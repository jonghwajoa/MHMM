package xyz.mhmm.friend.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;

@SuppressWarnings("serial")
public class OwnSelfAddFriendException extends BusinessException {

	public OwnSelfAddFriendException() {
		super(ErrorCode.OWN_SELF_ADD_FRIEND);
	}

}
