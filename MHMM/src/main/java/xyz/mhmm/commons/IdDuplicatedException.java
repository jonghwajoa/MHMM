package xyz.mhmm.commons;

import lombok.Getter;

@Getter

public class IdDuplicatedException extends BusinessException {
	
	public IdDuplicatedException() {
        super(ErrorCode.ID_DUPLICATION);
    }

}
