package xyz.mhmm;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import xyz.mhmm.domain.Login;
import xyz.mhmm.domain.User;
import xyz.mhmm.persistence.LoginRepository;
import xyz.mhmm.persistence.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
public class UserTests {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private LoginRepository loginRepo;

	public void insertTest() {
		User newUser = new User();
		newUser.setName("MHMM");
		newUser.setEmail("test@mmhm.xyz");
		newUser.setSex('M');
		newUser.setPhone("010-0000-0000");
		userRepo.save(newUser);
	}

	public void selectTest() {
		userRepo.findById(1L).ifPresent(user -> {
			System.out.println(user.toString());
		});
	}

	public void updateTest() {
		userRepo.findById(1L).ifPresent(user -> {
			user.setName("수정된 이름");
			userRepo.save(user);
		});
	}

	public void deleteTest() {
		userRepo.deleteById(1L);
	}

	@Transactional
	public void createUserLogicTest() {

		User newUser = new User();
		newUser.setName("MHMM");
		newUser.setEmail("test@mmhm.xyz");
		newUser.setSex('M');
		newUser.setPhone("010-0000-0000");
		User result = userRepo.save(newUser);
		System.out.println(result.getNo());

		Login login = new Login();
		login.setUser(result);
		login.setPw("비밀번호얌");
		login.setId("아이디얌");
		loginRepo.save(login);
	}

	@Test
	@Transactional
	public void updateUserLogicTest() {
		loginRepo.findById(5L).ifPresent(user -> {
			System.out.println(user);

		});
	}
}
