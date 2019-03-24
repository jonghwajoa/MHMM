package xyz.mhmm.mypage;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyPageDTO {

	@Getter
	@Setter
	@ToString
	public static class PasswordChange {
		private Long userNo;
		
		@NotBlank(message = "현재 비밀번호는 반드시 입력해야 합니다.")
		@Length(min = 5, message = "현재 비밀번호는 최소 5자리 이상입니다.")
		@Length(max = 100, message = "현재 비밀번호는 최대 100자리 입니다.")
		private String curPW;
		@NotBlank(message = "변경할 비밀번호는 반드시 입력해야 합니다.")
		@Length(min = 5, message = "변경할 비밀번호는 최소 5자리 이상입니다.")
		@Length(max = 100, message = "변경할 비밀번호는 최대 100자리 입니다.")
		private String changePw;
		@NotBlank(message = "변경할 비밀번호의 확인비밀번호는 반드시 입력해야 합니다.")
		@Length(min = 5, message = "변경할 비밀번호의 확인비밀번호는 최소 5자리 이상입니다.")
		@Length(max = 100, message = "변경할 비밀번호의 확인비밀번호는 최대 100자리 입니다.")
		private String changePwCheck;
	}
}
