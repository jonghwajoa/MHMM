package xyz.mhmm.commons;

import lombok.Getter;

@Getter

public class IdDuplicatedException extends BusinessException {
	
	public IdDuplicatedException() {
        super(ErrorCode.EMAIL_DUPLICATION);
    }

}
