package xyz.mhmm.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.mhmm.chatRoom.ChatRoomDTO;
import xyz.mhmm.chatRoom.ChatRoomService;

@Component
public class ChatHandler extends TextWebSocketHandler {

//	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private ChatRoomService chatRoomService;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId() + "연결됨");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(session.getId() + " " + message.getPayload());
		MessageDTO dto = objectMapper.readValue(message.getPayload(), MessageDTO.class);
		ChatRoomDTO chatRoomDTO = chatRoomService.getChatRoom(dto.getChatRoomId());
		chatRoomDTO.handleMessage(session, dto, objectMapper);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session + "연결끊김");
	}

}
