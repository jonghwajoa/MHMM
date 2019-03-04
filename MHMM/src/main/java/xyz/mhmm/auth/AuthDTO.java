package xyz.mhmm.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class AuthDTO {

	@Data
	public static class Create {

		private Long no;

		@NotBlank(message ="이메일은 반드시 입력해야 합니다.")
		@Email(message = "이메일 형식이 올바르지 않습니다.")
		@Length(min = 5, message = "이메일은 최소 5자리 이상입니다.")
		@Length(max = 50, message = "이메일 최대 길이는 50입니다.")
		private String email;

		@NotBlank(message ="이름은 반드시 입력해야 합니다.")
		@Length(min = 2, message = "이름은 최소 2자리 이상입니다.")
		@Length(max = 10, message = "이름은 최대 길이 10입니다.")
		@Pattern(regexp = "\\S{2,10}", message = "이름은 2~10자로 입력해주세요.")
		private String name;

		@NotBlank(message ="아이디는 반드시 입력해야 합니다.")
		@Length(min = 5, message = "아이디는 최소 5자리 이상입니다.")
		@Length(max = 20, message = "아이디는 최대 20자리 입니다.")
		private String id;

		@NotBlank(message ="비밀번호는 반드시 입력해야 합니다.")
		@Length(min = 5, message = "비밀번호는 최소 5자리 이상입니다.")
		@Length(max = 100, message = "비밀번호는 최대 100자리 입니다.")
		private String pw;

		@NotBlank(message ="비밀번호확인은 반드시 입력해야 합니다.")
		@Length(min = 5, message = "비밀번호는 최소 5자리 이상입니다.")
		@Length(max = 100, message = "비밀번호는 최대 100자리 입니다.")
		private String pwCheck;
	}

	@Data
	public static class CreateResponse {
		private Long no;
		private String email;
		private String name;
		private String id;
	}
	
	@Setter
	@Getter
	public static class Login {
		@NotBlank(message ="아이디는 반드시 입력해야 합니다.")
		@Length(min = 5, message = "아이디는 최소 5자리 이상입니다.")
		@Length(max = 20, message = "아이디는 최대 20자리 입니다.")
		private String id;

		@NotBlank(message ="비밀번호는 반드시 입력해야 합니다.")
		@Length(min = 5, message = "비밀번호는 최소 5자리 이상입니다.")
		@Length(max = 100, message = "비밀번호는 최대 100자리 입니다.")
		private String pw;
	}
	
	@Setter
	@Getter
	public static class LoginResponse {
		private String id;
	}

	public static AuthDTO.CreateResponse convertResponse(AuthDTO.Create origin) {
		AuthDTO.CreateResponse newUser = new AuthDTO.CreateResponse();
		newUser.setNo(origin.getNo());
		newUser.setEmail(origin.getEmail());
		newUser.setId(origin.getId());
		newUser.setName(origin.getName());
		return newUser;
	}
	
	public static AuthDTO.LoginResponse convertLoginResponse(AuthDTO.Login origin) {
		AuthDTO.LoginResponse newUser = new AuthDTO.LoginResponse();
		newUser.setId(origin.getId());
		return newUser;
	}

}
