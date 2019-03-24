package xyz.mhmm.mypage;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class MyPageValidation {
	public void pwEqCheck(MyPageDTO.PasswordChange dto, Errors errors) {

		if (errors.hasErrors()) {
			return;
		}

		if (!dto.getChangePw().equals(dto.getChangePwCheck())) {
			errors.rejectValue("changePwCheck", "Not Equal", "변경할 비밀번호가 확인과 일치하지 않습니다.");
		}
	}
}
