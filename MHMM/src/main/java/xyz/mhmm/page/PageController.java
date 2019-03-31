package xyz.mhmm.page;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import xyz.mhmm.utils.SessionAttribute;

@Controller
public class PageController {

	@ModelAttribute
	public void authCheck(Model model, HttpSession session) {
		model.addAttribute("authCheck", session.getAttribute(SessionAttribute.USER_ID) != null ? true : false);
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/auth/login")
	public String login() {
		return "/auth/login";
	}

	@GetMapping("/auth/signup")
	public String signup() {
		return "auth/signup";
	}

	@GetMapping("/messenger")
	public String messenger(HttpSession session, Model model) {
		model.addAttribute("userId", (String) session.getAttribute(SessionAttribute.USER_ID));
		return "messenger/index";
	}

	@GetMapping("/messenger/chatroom/{id}")
	public String chatRoom(@PathVariable Long id, HttpSession session, Model model) {

		model.addAttribute("userNo", session.getAttribute(SessionAttribute.USER_NO));
		model.addAttribute("userId", session.getAttribute(SessionAttribute.USER_ID));
		model.addAttribute("roomNo", id);

		return "messenger/chatRoom";
	}

	@GetMapping("/mypage")
	public String chatRoom(HttpSession session, Model model) {
		return "mypage/mypage";
	}

}
