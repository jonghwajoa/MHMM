package xyz.mhmm.mypage;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.mhmm.exception.ErrorCode;
import xyz.mhmm.exception.ErrorResponse;

@RestController
@RequestMapping("/api/mypage")
public class MyPageRestController {

	private MyPageService service;
	private MyPageValidation myPageValidation;

	public MyPageRestController(MyPageService myPageService, MyPageValidation myPageValidation) {
		this.service = myPageService;
		this.myPageValidation = myPageValidation;
	}

	@PatchMapping("/password")
	public ResponseEntity<?> passwordChange(@RequestBody @Valid MyPageDTO.PasswordChange dto, BindingResult result,
			HttpSession session) {
		myPageValidation.pwEqCheck(dto, result);

		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		dto.setUserNo((Long) session.getAttribute("userNo"));
		service.passwordChange(dto);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
