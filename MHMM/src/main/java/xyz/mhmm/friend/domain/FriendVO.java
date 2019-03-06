package xyz.mhmm.friend.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FriendVO {

	// 친구 추가하는 사람
	private Long follower;

	// 친구추가 당하는 사람
	private Long following;
}
