package xyz.mhmm.chatRoom;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.mhmm.messenger.MessageDTO;
import xyz.mhmm.messenger.MessageType;

@Getter
@Setter
@ToString
public class ChatRoomDTO {

	private Long id;
	private Set<WebSocketSession> sessions = new HashSet<>();

	public ChatRoomDTO(Long id) {
		this.id = id;
	}

	public void handleMessage(WebSocketSession session, MessageDTO chatMessage, ObjectMapper objectMapper) {

		if (chatMessage.getType() == MessageType.JOIN) {
			join(session);
			chatMessage.setMessage(chatMessage.getWriter() + "님이 입장했습니다.");
		}

		try {
			send(chatMessage, objectMapper);
		} catch (Exception e) {
			System.out.println("에러발생.......");
			System.out.println(e);
		}
	}

	private void join(WebSocketSession session) {
		sessions.add(session);
	}

	private <T> void send(T messageObject, ObjectMapper objectMapper) throws JsonProcessingException, IOException {
		try {
			TextMessage message = new TextMessage(objectMapper.writeValueAsString(messageObject));
			sessions.parallelStream().forEach(session -> sendMessage(session, message));
		} catch (JsonProcessingException e) {
			System.out.println("에러발생.......");
			System.out.println(e);
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	public void sendMessage(WebSocketSession session, TextMessage message) {
		try {
			session.sendMessage(message);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/*
	 * @Getter
	 * 
	 * @Setter
	 * 
	 * @ToString public static class ChatRoomCreate {
	 * 
	 * @NotBlank
	 * 
	 * @Min(value = 0, message = "ID값은 최소0 입니다.") private Long id;
	 * 
	 * @Length(max = 50, message = "채팅방 제목은 최대 50글자 입니다.") private String title; }
	 */
}
