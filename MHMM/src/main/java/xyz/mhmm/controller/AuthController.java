package xyz.mhmm.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		System.out.println("�뿤�뿤 �뿬�뀘湲곕떦");
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
			System.out.println("�뿬湲대뜲..?");
			return "auth/signup";
		}
		System.out.println("�뿬湲댁븘�땶�뜲...");
		return "index";
	}

}
