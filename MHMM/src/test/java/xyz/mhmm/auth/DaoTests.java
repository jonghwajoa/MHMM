package xyz.mhmm.auth;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.domain.LoginDTO;
import xyz.mhmm.domain.UserDTO;
import xyz.mhmm.persistence.LoginDAO;
import xyz.mhmm.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@Rollback
public class DaoTests {

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private UserDAO userDAO;

	@Test
	public void test1_createUser() {
		UserDTO user = createUserDtoForUser();
		userDAO.create(user);
	}

	@Test
	public void test2_createLogin() {
		UserDTO user = createUserDtoForLogin();
		Long userNo = loginDAO.create(user);
		assertThat(userNo).isNotNull();
	}

	@Test
	public void test3_selectExistIdTest() {
		boolean result = loginDAO.findExistById("userid");
		assertThat(result).isTrue();

		result = loginDAO.findExistById("jonghwaid");
		assertThat(result).isFalse();
	}

	@Test
	public void test3_selectExistEmailTest() {
		boolean result = userDAO.findExistByEmail("dqwdwq");
		assertThat(result).isFalse();

		result = userDAO.findExistByEmail("mhmm@mhmm.xyz");
		assertThat(result).isTrue();
	}

	@Test
	public void test4_updateToNameTest() {
		UserDTO user = createUserDtoForUser();
		user.setName("수정한이름");
		user.setNo(1L);
		userDAO.updateToName(user);
	}

	@Test
	public void test4_updateToEmailTest() {
		UserDTO user = createUserDtoForUser();
		user.setEmail("update@mhmm.xyz");
		user.setNo(1L);
		userDAO.updateToEmail(user);
	}

	@Test
	public void test4_updateToPwTest() {
		LoginDTO user = createLoginDto();
		user.setPw("바꾼비밀번호");
		loginDAO.updateToPw(user);
	}

	@Test
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
		user.setName("종화");
		return user;
	}

}