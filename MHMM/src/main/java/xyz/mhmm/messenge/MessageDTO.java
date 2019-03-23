package xyz.mhmm.messenge;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MessageDTO {

	private Long chatroom_no;
	private Long user_no;
	private String user_name;
	private String message;
	private MessageType type;
	private String created_at;

	@ToString
	@Getter
	@Setter
	public static class noOnly {
		@NotNull(message = "채팅방 번호는 반드시 입력해야 합니다.")
		@Min(value = 0L, message = "채팅방 번호는 0 이상의 값이어야 합니다.")
		@Max(value = Long.MAX_VALUE, message = "채팅방 번호는 " + Long.MAX_VALUE + "이하여야 합니다.")
		private Long chatroom_no;
		private Long user_no;
	}
}
