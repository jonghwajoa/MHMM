package xyz.mhmm.messenge.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MessageVO {

	private Long no;
	private String message;
	private String user_no;
	private String user_name;
	private String created_at;
}
