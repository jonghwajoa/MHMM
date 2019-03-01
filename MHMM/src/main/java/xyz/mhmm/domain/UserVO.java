package xyz.mhmm.domain;

import javax.validation.constraints.NotNull;

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

	@NotNull
	@Length(min = 5, message = "아이디는 최소 5자리 이상입니다.")
	@Length(max = 20, message = "아이디는 최대 20자리 입니다.")
	private String id;

	@NotNull
	@Length(min = 5, message = "비밀번호는 최소 5자리 이상입니다.")
	@Length(max = 100, message = "비밀번호는 최대 100자리 입니다.")
	private String pw;

}
