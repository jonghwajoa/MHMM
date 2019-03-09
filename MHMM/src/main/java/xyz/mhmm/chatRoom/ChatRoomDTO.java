package xyz.mhmm.chatRoom;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ChatRoomDTO {

	@Getter
	@Setter
	@ToString
	public static class ChatRoomCreate {
		@NotBlank
		@Min(value = 0, message = "ID값은 최소0 입니다.")
		private Long id;

		@Length(max = 50, message = "채팅방 제목은 최대 50글자 입니다.")
		private String title;
	}
}
