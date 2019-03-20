package xyz.mhmm.chatRoom.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OneToOneVO {

	private Long no;
	private Long from_userno;
	private Long to_userno;

	@ToString
	@Getter
	public static class findAllVO {
		private Long no;
		private String from_user_id;
		private String to_user_id;

	}
}
