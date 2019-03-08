package xyz.mhmm.chatRoom.dao;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ChatRoom {

	private Long no;
	
	@NotNull
	@Length(max = 50, message = "채팅방 제목은 최대 50글자 입니다.")
	private String title;
}
