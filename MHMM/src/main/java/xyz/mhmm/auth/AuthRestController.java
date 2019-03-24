package xyz.mhmm.auth;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.mhmm.auth.domain.LoginVO;
import xyz.mhmm.exception.ErrorCode;
import xyz.mhmm.exception.ErrorResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

	private AuthService authService;
	private AuthValidation userValidation;

	public AuthRestController(AuthService authService, AuthValidation userValidation) {
		this.authService = authService;
		this.userValidation = userValidation;
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
	public ResponseEntity<?> loginPOST(@RequestBody @Valid AuthDTO.Login dto, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		LoginVO loginVO = authService.Login(dto);
		session.setAttribute("userId", loginVO.getId());
		session.setAttribute("userNo", loginVO.getNo());

		return new ResponseEntity<>(AuthDTO.convertLoginResponse(dto), HttpStatus.OK);
	}

	@GetMapping(path = "/search", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> searchUser(@Valid AuthDTO.searchUser dto, BindingResult result) {
		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		LoginVO vo = authService.existUserById(dto.getId());

		if (vo == null) {
			return new ResponseEntity<>(ErrorCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(AuthDTO.convertSearchResponse(vo), HttpStatus.OK);
	}

}
