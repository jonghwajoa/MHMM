package xyz.mhmm.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.mhmm.commons.BusinessException;
import xyz.mhmm.commons.EmailDuplicatedException;
import xyz.mhmm.commons.ErrorCode;
import xyz.mhmm.commons.ErrorResponse;
import xyz.mhmm.commons.ErrorResponse.FieldError;
import xyz.mhmm.commons.IdDuplicatedException;
import xyz.mhmm.domain.LoginVO;
import xyz.mhmm.dto.AuthDTO;
import xyz.mhmm.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

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
	@ResponseBody
	public ResponseEntity<?> signupPOST(@RequestBody @Valid AuthDTO.Create dto, BindingResult result,
			Model model) {

		System.out.println(dto.toString());
		if (result.hasErrors()) {

			List<FieldError> errors = result.getFieldErrors().stream()
					.map(obj -> new FieldError(obj.getField(), obj.getCode(), obj.getDefaultMessage()))
					.collect(Collectors.toList());

			ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, errors);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(AuthDTO.convertResponse(authService.create(dto)), HttpStatus.CREATED);
	}

	@ExceptionHandler({ EmailDuplicatedException.class, IdDuplicatedException.class })
	public ResponseEntity<?> handleUserDuplicatedException(BusinessException e) {
		return new ResponseEntity<>(new ErrorResponse(e.getErrorCode()), HttpStatus.BAD_REQUEST);
	}

}
