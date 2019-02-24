package xyz.mhmm.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import xyz.mhmm.domain.UserDTO;
import xyz.mhmm.persistence.LoginDAO;
import xyz.mhmm.persistence.UserDAO;
import xyz.mhmm.service.AuthService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthTests {

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private UserDAO userDAO;

	private static final Logger logger = LoggerFactory.getLogger(AuthTests.class);

	@Test
	public void test1_createUser() {
		UserDTO user = new UserDTO();
		user.setEmail("21322mhmm@mhmm.xyz");
		user.setName("종화");
		
		userDAO.create(user);
	}

	public void test3_selectExistIdTest() {
		boolean result = loginDAO.findExistById("jonghwaidddd");
		assertThat(result, is(equalTo(false)));

		result = loginDAO.findExistById("jonghwaid");
		assertThat(result, is(equalTo(true)));

	}

	public void test3_selectExistEmailTest() {
		boolean result = userDAO.findExistByEmail("dqwdwq");
		assertThat(result, is(equalTo(false)));

		result = userDAO.findExistByEmail("mhmm@mhmm.xyz");
		assertThat(result, is(equalTo(true)));
	}

}
