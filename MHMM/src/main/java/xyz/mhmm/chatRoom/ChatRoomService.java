package xyz.mhmm.chatRoom;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {

	private Map<Long, ChatRoomDTO> list = new HashMap<>();

	public ChatRoomDTO chatRoomList(Long id) {

		if (list.get(id) == null) {
			list.put(id, new ChatRoomDTO(id));
			System.out.println("생성");
		}
		return list.get(id);
	}

	public ChatRoomDTO getChatRoom(Long id) {
		return list.get(id);
	}
}
