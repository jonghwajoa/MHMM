package xyz.mhmm.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.mhmm.auth.exception.EmailDuplicatedException;
import xyz.mhmm.auth.exception.IdDuplicatedException;
import xyz.mhmm.auth.exception.UserNotExistException;
import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;
import xyz.mhmm.exception.ErrorResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

	/*
	 * 제공해야하는 기능 로그인 , 회원가입 POST 요청 로그아웃 회원 탈퇴 기능 TODO : 비밀번호 변경
	 */

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthValidation userValidation;

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
