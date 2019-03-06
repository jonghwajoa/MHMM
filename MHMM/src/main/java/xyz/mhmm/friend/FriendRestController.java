package xyz.mhmm.friend;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.mhmm.auth.AuthDTO;
import xyz.mhmm.auth.domain.LoginVO;
import xyz.mhmm.auth.domain.UserVO;
import xyz.mhmm.exception.ErrorCode;
import xyz.mhmm.exception.ErrorResponse;

@RestController
@RequestMapping(path = "/api/friend", consumes = "application/json", produces = "application/json")
public class FriendRestController {

	@Autowired
	private FriendService friendService;

	@GetMapping(path = "/")
	public ResponseEntity<?> allFriend(HttpSession session) {
		return new ResponseEntity<>(friendService.findAll((Long) session.getAttribute("userNo")), HttpStatus.OK);
	}

	@PostMapping(path = "/")
	public ResponseEntity<?> addFriend(@Valid @RequestBody FriendDTO.Add dto, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		friendService.create((Long) session.getAttribute("userNo"), dto);

		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> searchUser(@ModelAttribute @Valid FriendDTO.Search id, BindingResult result) {
		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(FriendDTO.convertSearchResponse(friendService.search(id)), HttpStatus.OK);
	}

	@DeleteMapping(path = "/")
	public ResponseEntity<?> deleteFriend(@Valid @RequestBody FriendDTO.Delete dto, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		friendService.delete((Long) session.getAttribute("userNo"), dto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
