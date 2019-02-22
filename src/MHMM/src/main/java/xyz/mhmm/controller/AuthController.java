package xyz.mhmm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.mhmm.domain.LoginVO;
import xyz.mhmm.domain.UserVO;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/login")
	public String loginGET() {
		logger.info("auth/login");
		return "auth/login";
	}

	@PostMapping("/login")
	public String loginPOST(LoginVO loginVO) {
		System.out.println(loginVO.toString());
		System.out.println("헤헤 여ㅑ기당");
		return "redirect:/";
	}

}
