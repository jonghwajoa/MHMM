package xyz.mhmm.auth.exception;

import lombok.Getter;
import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;

@Getter
public class EmailDuplicatedException extends BusinessException {

	public EmailDuplicatedException() {
        super(ErrorCode.EMAIL_DUPLICATION);
    }

}
