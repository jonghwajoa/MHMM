package xyz.mhmm.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long no;

	@NotNull
	@Email(message = "�씠硫붿씪 �삎�떇�씠 �삱諛붾Ⅴ吏� �븡�뒿�땲�떎.")
	@Length(min = 5, message = "�씠硫붿씪�� 理쒖냼 5�옄由� �씠�긽�엯�땲�떎.")
	@Length(max = 50, message = "�씠硫붿씪 理쒕� 湲몄씠�뒗 50�엯�땲�떎.")
	private String email;

	@NotNull
	@Length(min = 2, message = "�씠由꾩� 理쒖냼 2�옄由� �씠�긽�엯�땲�떎.")
	@Length(max = 10, message = "�씠由꾩� 理쒕� 湲몄씠 10�엯�땲�떎.")
	@Pattern(regexp = "\\S{2,10}", message = "�씠由꾩� 2~10�옄濡� �엯�젰�빐二쇱꽭�슂.")
	private String name;

	@NotNull
	@Length(min = 5, message = "�븘�씠�뵒�뒗 理쒖냼 5�옄由� �씠�긽�엯�땲�떎.")
	@Length(max = 20, message = "�븘�씠�뵒�뒗 理쒕� 20�옄由� �엯�땲�떎.")
	private String id;

	@NotNull
	@Length(min = 5, message = "鍮꾨�踰덊샇�뒗 理쒖냼 5�옄由� �씠�긽�엯�땲�떎.")
	@Length(max = 100, message = "鍮꾨�踰덊샇�뒗 理쒕� 100�옄由� �엯�땲�떎.")
	private String pw;

}
