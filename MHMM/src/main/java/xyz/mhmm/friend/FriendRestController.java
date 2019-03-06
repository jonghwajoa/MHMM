package xyz.mhmm.friend;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.mhmm.auth.AuthDTO;
import xyz.mhmm.auth.exception.UserNotExistException;
import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;
import xyz.mhmm.exception.ErrorResponse;
import xyz.mhmm.friend.exception.OwnSelfAddFriendException;

@RestController
@RequestMapping("/api/friend")
public class FriendRestController {

	@Autowired
	private FriendService friendService;

	@GetMapping(path = "/", consumes = "application/json", produces = "application/json")
	public void allFriend(HttpSession session) {

	}

	// 경로를 어떻게 설계할것인가.. 흐음....
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addFriend(@Valid @RequestBody FriendDTO.Add dto, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		friendService.create((Long) session.getAttribute("userNo"), dto);

		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@GetMapping(path = "/search", consumes = "application/json", produces = "application/json")
	public void searchUser() {

	}

	@DeleteMapping(path = "/delete", consumes = "application/json", produces = "application/json")
	public void deleteFriend() {

	}

}
