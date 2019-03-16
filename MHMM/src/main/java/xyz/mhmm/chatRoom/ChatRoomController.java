package xyz.mhmm.chatRoom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatRoomController {

	ChatRoomService chatRoomService;

	public ChatRoomController(ChatRoomService chatRoomService) {
		this.chatRoomService = chatRoomService;
	}

	@GetMapping("/messenger/chatroom/{id}")
	public String chatRoom(@PathVariable Long id, Model model) {

		chatRoomService.chatRoomList(id);

		model.addAttribute("No", id);
		return "messenger/chatRoom";
	}

}
