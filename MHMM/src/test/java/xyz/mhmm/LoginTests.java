package xyz.mhmm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import xyz.mhmm.domain.Login;
import xyz.mhmm.persistence.LoginRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTests {

	@Autowired
	private LoginRepository loginRepo;

	@Test
	public void insertTest() {
		
		
		
		
	}
}
