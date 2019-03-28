package xyz.mhmm.messenge;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import xyz.mhmm.auth.SessionAttribute;
import xyz.mhmm.exception.ErrorCode;
import xyz.mhmm.exception.ErrorResponse;

@RestController
public class MessageRestController {

	private final SimpMessagingTemplate template;
	private final MessageService service;
	private final String url = "/app/chat/room/";

	public MessageRestController(final SimpMessagingTemplate template, MessageService messageService) {
		this.template = template;
		this.service = messageService;
	}

	@MessageMapping("/chat/message")
	public void message(final MessageDTO dto) {
		System.out.println("일단 여기온다..");
		dto.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH시 mm분 ss초").format(new java.util.Date()));
		service.create(dto);
		template.convertAndSend(url + dto.getChatroom_no(), dto);
	}

	@GetMapping("/api/message/onetoone/{chatroom_no}")
	public ResponseEntity<?> getChatMessage(@ModelAttribute @Valid MessageDTO.noOnly dto, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		dto.setUser_no((Long) session.getAttribute(SessionAttribute.USER_NO));
		return new ResponseEntity<>(service.oneToOneChatFindAll(dto), HttpStatus.OK);
	}
}
