package xyz.mhmm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

public class AuthDTO {

	@Data
	public static class Create {

		private Long no;

		@NotBlank
		@Email(message = "이메일 형식이 올바르지 않습니다.")
		@Length(min = 5, message = "이메일은 최소 5자리 이상입니다.")
		@Length(max = 50, message = "이메일 최대 길이는 50입니다.")
		private String email;

		@NotBlank
		@Length(min = 2, message = "이름은 최소 2자리 이상입니다.")
		@Length(max = 10, message = "이름은 최대 길이 10입니다.")
		@Pattern(regexp = "\\S{2,10}", message = "이름은 2~10자로 입력해주세요.")
		private String name;

		@NotBlank
		@Length(min = 5, message = "아이디는 최소 5자리 이상입니다.")
		@Length(max = 20, message = "아이디는 최대 20자리 입니다.")
		private String id;

		@NotBlank
		@Length(min = 5, message = "비밀번호는 최소 5자리 이상입니다.")
		@Length(max = 100, message = "비밀번호는 최대 100자리 입니다.")
		private String pw;
	}

	@Data
	public static class CreateResponse {
		private String email;
		private String name;
		private String id;
		private String pw;
	}

}
