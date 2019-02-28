package xyz.mhmm.commons;

import lombok.Getter;

@Getter
public class EmailDuplicatedException extends BusinessException {

	public EmailDuplicatedException() {
        super(ErrorCode.EMAIL_DUPLICATION);
    }

}
