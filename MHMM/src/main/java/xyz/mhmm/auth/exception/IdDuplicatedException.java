package xyz.mhmm.auth.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.utils.ErrorCode;

@SuppressWarnings("serial")
public class IdDuplicatedException extends BusinessException {

	public IdDuplicatedException() {
		super(ErrorCode.ID_DUPLICATION);
	}

}
