package xyz.mhmm.page;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

	@ModelAttribute
	public void authCheck(Model model, HttpSession session) {
		model.addAttribute("authCheck", session.getAttribute("userId") != null ? true : false);
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
		model.addAttribute("userId", (String) session.getAttribute("userId"));

		return "messenger/index";
	}

	@GetMapping("/messenger/chatroom/{id}")
	public String chatRoom(@PathVariable Long id, HttpSession session, Model model) {

		model.addAttribute("userId", session.getAttribute("userId"));
		model.addAttribute("roomNo", id);

		System.out.println(session.getAttribute("userId"));
		return "messenger/chatRoom";
	}

}