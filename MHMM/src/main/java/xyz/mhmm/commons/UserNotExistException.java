package xyz.mhmm.commons;

public class UserNotExistException extends BusinessException {

	public UserNotExistException() {
        super(ErrorCode.USER_NOT_EXIST);
    }
}
