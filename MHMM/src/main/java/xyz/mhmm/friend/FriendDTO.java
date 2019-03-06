package xyz.mhmm.friend;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

public class FriendDTO {

	@Setter
	@Getter
	@ToString
	public static class Add {
		@NotBlank(message = "아이디는 반드시 입력해야 합니다.")
		@Length(min = 5, message = "아이디는 최소 5자리 이상입니다.")
		@Length(max = 20, message = "아이디는 최대 20자리 입니다.")
		private String id;
	}
}
