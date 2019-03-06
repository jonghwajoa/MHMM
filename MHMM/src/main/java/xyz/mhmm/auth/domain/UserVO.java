package xyz.mhmm.auth.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

	private Long no;

	@NotBlank(message = "이메일은 반드시 입력해야 합니다.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	@Length(min = 5, message = "이메일은 최소 5자리 이상입니다.")
	@Length(max = 50, message = "이메일 최대 길이는 50입니다.")
	private String email;

	@NotBlank(message = "이름은 반드시 입력해야 합니다.")
	@Length(min = 2, message = "이름은 최소 2자리 이상입니다.")
	@Length(max = 10, message = "이름은 최대 길이 10입니다.")
	@Pattern(regexp = "\\S{2,10}", message = "이름은 2~10자로 입력해주세요.")
	private String name;

}