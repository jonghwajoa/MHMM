package xyz.mhmm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.mhmm.commons.ErrorResponse;
import xyz.mhmm.domain.LoginVO;
import xyz.mhmm.dto.AuthDTO;
import xyz.mhmm.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;

	@GetMapping("/login")
	public String loginGET() {
		return "auth/login";
	}

	@PostMapping("/login")
	public String loginPOST(LoginVO loginVO) {
		System.out.println(loginVO.toString());
		return "redirect:/";
	}

	@GetMapping("/signup")
	public String signupGET() {
		return "auth/signup";
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signupPOST(@RequestBody @ModelAttribute @Valid AuthDTO.Create dto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			ErrorResponse errorResponse = new ErrorResponse();
			result.getAllErrors().forEach(e -> errorResponse.add(e.getCode(), e.getDefaultMessage()));
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

//		authService.create(dto);

		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
}
