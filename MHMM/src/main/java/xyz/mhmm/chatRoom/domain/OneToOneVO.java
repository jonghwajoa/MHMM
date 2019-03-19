package xyz.mhmm.chatRoom.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OneToOneVO {

	private Long no;
	private Long from_userno;
	private Long to_userno;
}
