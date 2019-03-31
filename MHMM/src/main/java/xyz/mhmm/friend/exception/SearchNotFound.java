package xyz.mhmm.friend.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.utils.ErrorCode;

@SuppressWarnings("serial")
public class SearchNotFound extends BusinessException {

	public SearchNotFound() {
		super(ErrorCode.FRIEND_SERACH_NOT_FOUND);
	}

}
