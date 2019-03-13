package xyz.mhmm.messenger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChatHandler extends TextWebSocketHandler {
	private static Logger logger = LoggerFactory.getLogger(ChatHandler.class);
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		System.out.println(session.getId() + "연결됨");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("{}로 부터 {} 받음 : " + session.getId() + " " + message.getPayload());
		MessageDTO dto = objectMapper.readValue(message.getPayload(), MessageDTO.class);

		System.out.println(dto.getMessage() + " " + dto.getWriter());
		for (WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(objectMapper.writeValueAsString(dto)));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		logger.info("{} 연결 끊김", session.getId());
	}

}
