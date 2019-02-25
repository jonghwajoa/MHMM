package xyz.mhmm.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.mhmm.domain.LoginDTO;
import xyz.mhmm.domain.UserDTO;
import xyz.mhmm.persistence.LoginDAO;
import xyz.mhmm.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthTests {

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private UserDAO userDAO;

	public void test1_createUser() {
		UserDTO user = createUserDtoForUser();
		userDAO.create(user);
	}
	
	public void test2_createLogin() {
		UserDTO user = createUserDtoForLogin();
		loginDAO.create(user);
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
	
	public void test4_updateToNameTest() {
		UserDTO user =createUserDtoForUser();
		user.setName("�닔�젙�븳�씠由�");
		user.setNo(1L);
		userDAO.updateToName(user);
	}
	
	public void test4_updateToEmailTest() {
		UserDTO user =createUserDtoForUser();
		user.setEmail("update@mhmm.xyz");
		user.setNo(1L);
		userDAO.updateToEmail(user);
	}

	public void test4_updateToPwTest() {
		LoginDTO user= createLoginDto();
		user.setPw("諛붽씔鍮꾨�踰덊샇");
		loginDAO.updateToPw(user);
	}
	
	public void test5_deleteUser() {
		userDAO.delete(1L);
	}
	
	public UserDTO createUserDtoForLogin() {
		UserDTO user = new UserDTO();
		user.setId("userid");
		user.setPw("userpw");
		user.setNo(33L);
		return user;
	}
	
	public LoginDTO createLoginDto() {
		LoginDTO user = new LoginDTO();
		user.setId("userid");
		user.setPw("userpw");
		user.setNo(33L);
		return user;
	}
	
	public UserDTO createUserDtoForUser() {
		UserDTO user = new UserDTO();
		user.setEmail("21322mhmm@mhmm.xyz");
		user.setName("醫낇솕");
		return user;
	}

}
