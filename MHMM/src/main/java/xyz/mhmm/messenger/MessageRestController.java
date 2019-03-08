package xyz.mhmm.messenger;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/message", consumes = "application/json", produces = "application/json")
public class MessageRestController {

	@GetMapping("/")
	public String index(HttpSession session) {
		System.out.println("엥...?");
		System.out.println(session.toString());
		System.out.println(session.getAttribute("userId"));
		System.out.println(session.getAttribute("userNo"));
		
		return "ㅎㅎ..?";
	}
}
