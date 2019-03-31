package xyz.mhmm.friend;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.mhmm.auth.domain.UserVO;

public class FriendDTO {

	@Setter
	@Getter
	@ToString
	public static class Add {
		@NotBlank(message = "아이디는 반드시 입력해야 합니다.")
		@Length(min = 5, message = "아이디는 최소 5자리 이상입니다.")
		@Length(max = 20, message = "아이디는 최대 20자리 입니다.")
		private String id;
	}

	@Setter
	@Getter
	@ToString
	public static class Delete {
		@NotNull(message = "삭제할 유저를 반드시 입력해야 합니다.")
		@Min(value = 0L, message = "회원 고유번호는 0 이상의 값이어야 합니다.")
		@Max(value = Long.MAX_VALUE, message = "회원 고유번호는 " + Long.MAX_VALUE + "이하여야 합니다.")
		private Long no;
	}

	@Setter
	@Getter
	@ToString
	public static class Search {
		@NotBlank(message = "아이디는 반드시 입력해야 합니다.")
		@Length(min = 5, message = "아이디는 최소 5자리 이상입니다.")
		@Length(max = 20, message = "아이디는 최대 20자리 입니다.")
		private String id;
	}

	@Setter
	@Getter
	@ToString
	public static class searchrResponse {
		private Long no;
		private String id;
		private String name;
		private String photo;
	}

	public static FriendDTO.searchrResponse convertSearchResponse(UserVO origin) {
		FriendDTO.searchrResponse dto = new FriendDTO.searchrResponse();
		dto.setId(origin.getId());
		dto.setName(origin.getName());
		dto.setNo(origin.getNo());
		dto.setPhoto(origin.getPhoto_path());
		return dto;
	}
	
	
}
