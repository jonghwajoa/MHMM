package xyz.mhmm.auth.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserVO {

	private Long no;
	private String id;
	private String email;
	private String name;
	private String photo_path;

}
