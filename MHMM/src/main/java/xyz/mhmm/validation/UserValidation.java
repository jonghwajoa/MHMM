package xyz.mhmm.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import xyz.mhmm.dto.AuthDTO;

@Component
public class UserValidation {
	
	
	public void pwEqCheck(AuthDTO.Create dto, Errors errors) {
		
		if(errors.hasErrors()) {
			return;
		}
		
		if(!dto.getPw().equals(dto.getPwCheck())) {
			errors.rejectValue("pw", "Not Equal", "비밀번호 입력값이 올바르지 않습니다.");
		}
	}
}
