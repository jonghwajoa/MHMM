package xyz.mhmm.messenger.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MessageVO {

	private Long no;
	private String content;
	private String message;
	private String user_no;
	private String user_name;
	private Timestamp created_at;
}
