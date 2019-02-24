package xyz.mhmm.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.mhmm.domain.LoginDTO;
import xyz.mhmm.domain.UserDTO;

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
	public String loginPOST(LoginDTO loginVO) {
		System.out.println(loginVO.toString());
		System.out.println("헤헤 여ㅑ기당");
		return "redirect:/";
	}

	@GetMapping("/signup")
	public String signupGET() {
		return "auth/signup";
	}

	@PostMapping("/signup")
	public String signupPOST(@ModelAttribute("UserVO") @Valid UserDTO userDTO, BindingResult bindingResult,
			Model model) {
		System.out.println(userDTO);
		if (bindingResult.hasErrors()) {
			System.out.println("여긴데..?");
			return "auth/signup";
		}
		System.out.println("여긴아닌데...");
		return "index";
	}

}
