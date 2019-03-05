package xyz.mhmm.auth.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;

public class UserNotExistException extends BusinessException {

	public UserNotExistException() {
        super(ErrorCode.USER_NOT_EXIST);
    }
}
