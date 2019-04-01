package xyz.mhmm.friend;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.mhmm.auth.domain.UserVO;
import xyz.mhmm.exception.ErrorResponse;
import xyz.mhmm.utils.ErrorCode;
import xyz.mhmm.utils.SessionAttribute;

@RestController
@RequestMapping(path = "/api/friend", consumes = "application/json", produces = "application/json")
public class FriendRestController {

	private FriendService friendService;

	public FriendRestController(FriendService friendService) {
		this.friendService = friendService;
	}

	@GetMapping(path = "/")
	public ResponseEntity<?> allFriend(HttpSession session) {
		return new ResponseEntity<>(friendService.findAll((Long) session.getAttribute(SessionAttribute.USER_NO)),
				HttpStatus.OK);
	}

	@PostMapping(path = "/")
	public ResponseEntity<?> addFriend(@Valid @RequestBody FriendDTO.Add dto, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		UserVO friendVO = friendService.create((Long) session.getAttribute(SessionAttribute.USER_NO), dto);

		return new ResponseEntity<>(FriendDTO.convertSearchResponse(friendVO), HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> searchUser(@ModelAttribute @Valid FriendDTO.Search id, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		UserVO friendVO = friendService.search((Long) session.getAttribute(SessionAttribute.USER_NO), id);

		return new ResponseEntity<>(FriendDTO.convertSearchResponse(friendVO), HttpStatus.OK);
	}

	@DeleteMapping(path = "/")
	public ResponseEntity<?> deleteFriend(@Valid @RequestBody FriendDTO.Delete dto, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		// TODO : 로직 추가

		friendService.delete((Long) session.getAttribute(SessionAttribute.USER_NO), dto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
