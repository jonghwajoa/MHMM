package xyz.mhmm.messenger;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class MessageDTO {

	private Long chatRoomId;
	private String writer;
	private String message;
	private MessageType type;

}
