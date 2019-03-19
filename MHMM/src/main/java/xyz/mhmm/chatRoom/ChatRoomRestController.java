package xyz.mhmm.chatRoom;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.mhmm.chatRoom.domain.OneToOneVO;

@RestController
@RequestMapping(path = "/api/messenger/chatroom")

public class ChatRoomRestController {

	@Autowired
	OneToOneService oneToOneService;

	@GetMapping("/")
	public ResponseEntity<?> findAll(HttpSession session) {

		List<OneToOneVO> list = oneToOneService.findAll((Long) session.getAttribute("userNo"));
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
