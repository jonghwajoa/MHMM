package xyz.mhmm.chatRoom.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OneToOneDTO {

	@Setter
	@Getter
	@ToString
	public static class FindAndCreate {

		@Null(message = "no은 null이어야 합니다.")
		private Long no;
		@Null(message = "from_userno은 null이어야 합니다.")
		private Long from_userno;
		@NotNull(message = "to_userno는 notNull이어야 합니다.")
		@Min(value = 1L, message = "to_userno의 최소값은 1입니다.")
		private Long to_userno;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@ToString
	public static class roomNoResponse {
		private Long no;
	}

}
