package xyz.mhmm.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestController {

	private final SimpMessagingTemplate template;

	@Autowired
	public MessageRestController(SimpMessagingTemplate template) {
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
