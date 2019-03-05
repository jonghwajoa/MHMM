package xyz.mhmm.auth.exception;

import lombok.Getter;
import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;

@Getter
public class IdDuplicatedException extends BusinessException {
	
	public IdDuplicatedException() {
        super(ErrorCode.ID_DUPLICATION);
    }

}
