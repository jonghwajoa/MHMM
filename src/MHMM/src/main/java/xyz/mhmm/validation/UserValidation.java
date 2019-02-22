package xyz.mhmm.validation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import xyz.mhmm.domain.UserVO;

@Component
public class UserValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserVO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "EmptyOrWhitespace","전화번호는 공백이나 빈값일수 없습니다.");
	}
}
