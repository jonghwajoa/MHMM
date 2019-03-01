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
import xyz.mhmm.commons.UserNotExistException;
import xyz.mhmm.domain.LoginVO;
import xyz.mhmm.dto.AuthDTO;
import xyz.mhmm.service.AuthService;
import xyz.mhmm.validation.UserValidation;

@Controller
@RequestMapping("/auth")
public class AuthController {

	/*
	 * 제공해야하는 기능 로그인 , 회원가입 POST 요청 로그아웃 회원 탈퇴 기능 로그인 VIEW , 회원가입 VIEW
	 * 
	 * TODO : 비밀번호 변경
	 */

	@Autowired
	private AuthService authService;

	@Autowired
	private UserValidation userValidation;

	@GetMapping("/login")
	public String loginGET() {
		return "auth/login";
	}

	@GetMapping("/signup")
	public String signupGET() {
		return "auth/signup";
	}

	@PostMapping(path = "/signup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> signupPOST(@RequestBody @Valid AuthDTO.Create dto, BindingResult result) {
		userValidation.pwEqCheck(dto, result);

		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(AuthDTO.convertResponse(authService.create(dto)), HttpStatus.CREATED);
	}

	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> loginPOST(@RequestBody @Valid AuthDTO.Login dto, BindingResult result) {

		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(AuthDTO.convertLoginResponse(authService.Login(dto)), HttpStatus.OK);
	}

	@ExceptionHandler({ EmailDuplicatedException.class, IdDuplicatedException.class })
	public ResponseEntity<?> handleUserDuplicatedException(BusinessException e) {
		return new ResponseEntity<>(ErrorResponse.of(e), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotExistException.class)
	public ResponseEntity<?> handleUserNotExistException(BusinessException e) {
		return new ResponseEntity<>(ErrorResponse.of(e), HttpStatus.NOT_FOUND);
	}

}
