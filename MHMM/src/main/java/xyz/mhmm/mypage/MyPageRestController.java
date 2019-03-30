package xyz.mhmm.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import xyz.mhmm.auth.SessionAttribute;
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

	@GetMapping("/")
	public ResponseEntity<?> getMyPage(HttpSession session) {

		Long userNo = (Long) session.getAttribute(SessionAttribute.USER_NO);
		String path = service.getMyPage(userNo);

		return new ResponseEntity<>(path, HttpStatus.OK);
	}

	@PatchMapping("/password")
	public ResponseEntity<?> passwordChange(@RequestBody @Valid MyPageDTO.PasswordChange dto, BindingResult result,
			HttpSession session) {
		myPageValidation.pwEqCheck(dto, result);

		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		dto.setUserNo((Long) session.getAttribute(SessionAttribute.USER_NO));
		service.passwordChange(dto);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/photo")
	public ResponseEntity<?> changePhoto(final @RequestParam MultipartFile photo, final HttpServletRequest req,
			HttpSession session) {

		final String path = req.getSession().getServletContext().getRealPath("/img/userPhoto/");
		Long userNo = (Long) session.getAttribute(SessionAttribute.USER_NO);
		String saveFileName = service.changeProfilePhoto(photo, path, userNo);

		return new ResponseEntity<>(saveFileName, HttpStatus.CREATED);
	}
}
