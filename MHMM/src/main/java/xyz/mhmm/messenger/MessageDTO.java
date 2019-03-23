package xyz.mhmm.messenger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MessageDTO {

	private Long chatroom_no;
	private Long user_no;
	private String user_id;
	private String message;
	private MessageType type;
	private String created_at;

}
