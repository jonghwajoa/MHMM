package xyz.mhmm.chatRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import xyz.mhmm.messenger.MessageDTO;

@RestController
//@RequestMapping(path = "/api/messenger/chatroom")

public class ChatRoomRestController {

	@Autowired
	ChatRoomService chatRoomService;

	private final SimpMessagingTemplate template;

	@Autowired
	public ChatRoomRestController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/chat/join")
	public void join(MessageDTO message) {
		message.setMessage(message.getWriter() + "님이 입장하셨습니다.");
		template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
	}

	@MessageMapping("/chat/message")
	public void message(MessageDTO message) {
		template.convertAndSend("/subscribe/chat/room/" + message.getChatRoomId(), message);
	}

}

/*
 * @PostMapping("/") public ResponseEntity<?> create(@RequestBody @Valid
 * ChatRoomDTO.ChatRoomCreate dto, BindingResult result, HttpSession session) {
 * if (result.hasErrors()) { ErrorResponse errorResponse =
 * ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result); return new
 * ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); } return new
 * ResponseEntity<>(dto, HttpStatus.CREATED); }
 */