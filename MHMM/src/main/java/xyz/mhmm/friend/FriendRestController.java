package xyz.mhmm.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/friend")
public class FriendRestController {

	@Autowired
	private FriendService friendService;

	@GetMapping(path = "/", consumes = "application/json", produces = "application/json")
	public void allFriend() {
		System.out.println("all friend");
	}

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public void addFriend() {

	}
	
	@GetMapping(path = "/search", consumes = "application/json", produces = "application/json")
	public void searchUser() {

	}

	@DeleteMapping(path = "/delete", consumes = "application/json", produces = "application/json")
	public void deleteFriend() {

	}

}
