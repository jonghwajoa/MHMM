package xyz.mhmm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import xyz.mhmm.domain.User;
import xyz.mhmm.persistence.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class userTests {

	@Autowired
	private UserRepository userRepo;

	@Test
	public void insertTest() {
		User newUser = new User();
		newUser.setUid("유저아이디입니다^^");
		newUser.setUpw("유저 비밀번호 입니다.^^");
		userRepo.save(newUser);

	}


	public void selectTest() {
		userRepo.findById(1L).ifPresent(user -> {
			System.out.println(user.toString());
		});
	}

	public void updateTest() {
		userRepo.findById(1L).ifPresent(user -> {
			user.setUid("수정된 아이디 입니다.^^");
			user.setUpw("수정된 비밀번호 입니다.^^");
			userRepo.save(user);
		});
	}

	public void deleteTest() {
		userRepo.deleteById(1L);
	}

}
