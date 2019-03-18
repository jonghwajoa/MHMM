package xyz.mhmm.chatRoom.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OneToOneDTO {

	@Setter
	@Getter
	@ToString
	public static class create {
		private Long no;
		private Long from_userno;
		@NotBlank
		@Min(value = 0, message = "ID값은 최소0 입니다.")
		private Long to_userno;
	}
}
