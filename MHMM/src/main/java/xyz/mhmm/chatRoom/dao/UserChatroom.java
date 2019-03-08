package xyz.mhmm.chatRoom.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@NoArgsConstructor
public class UserChatroom {

	private Long userNo;
	private Long chatRoomNo;
	private Long nextIdx;
}
