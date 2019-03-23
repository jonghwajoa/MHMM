package xyz.mhmm.messenger;

import java.text.SimpleDateFormat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestController {

	private final SimpMessagingTemplate template;
	private final MessageService service;
	private final String url = "/subscribe/chat/room/";

	public MessageRestController(final SimpMessagingTemplate template, MessageService messageService) {
		this.template = template;
		this.service = messageService;
	}

	@MessageMapping("/chat/message")
	public void message(final MessageDTO dto) {
		dto.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH시 mm분").format(new java.util.Date()));
		service.create(dto);
		template.convertAndSend(url + dto.getChatroom_no(), dto);
	}
}

//@MessageMapping("/chat/join")
//public void join(final MessageDTO message) {
//	template.convertAndSend(url + message.getChatroom_no(), message);
//}
