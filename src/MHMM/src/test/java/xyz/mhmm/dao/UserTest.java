package xyz.mhmm.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.mhmm.domain.UserVO;
import xyz.mhmm.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class UserTest {

	@Autowired
	private UserDAO userDAO;

	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

	@Test
	public void createTest() {
		UserVO user = new UserVO();
		user.setEmail("mhmm@mhmm.xyz");
		user.setName("종화");
		user.setSex('남');
		user.setPhone("010-0000-0000");

		userDAO.create(user);
	}
}
