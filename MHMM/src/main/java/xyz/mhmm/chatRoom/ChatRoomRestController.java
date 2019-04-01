package xyz.mhmm.chatRoom;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.mhmm.chatRoom.domain.OneToOneVO;
import xyz.mhmm.chatRoom.dto.OneToOneDTO;
import xyz.mhmm.exception.ErrorResponse;
import xyz.mhmm.utils.ErrorCode;
import xyz.mhmm.utils.SessionAttribute;

@RestController
@RequestMapping(path = "/api/messenger/chatroom")
public class ChatRoomRestController {

	OneToOneService oneToOneService;

	public ChatRoomRestController(final OneToOneService oneToOneService) {
		this.oneToOneService = oneToOneService;
	}

	@GetMapping("/")
	public ResponseEntity<?> findAll(HttpSession session) {
		List<OneToOneVO.findAllVO> list = oneToOneService
				.findAll((Long) session.getAttribute(SessionAttribute.USER_NO));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> oneToOneChat(@Valid @RequestBody OneToOneDTO.FindAndCreate dto, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		dto.setFrom_userno((Long) session.getAttribute(SessionAttribute.USER_NO));
		Long roomNo = oneToOneService.create(dto);
		return new ResponseEntity<>(new OneToOneDTO.roomNoResponse(roomNo), HttpStatus.CREATED);
	}

}
