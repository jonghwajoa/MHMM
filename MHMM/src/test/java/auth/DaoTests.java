package auth;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.domain.LoginVO;
import xyz.mhmm.domain.UserVO;
import xyz.mhmm.dto.AuthDTO;
import xyz.mhmm.persistence.LoginDAO;
import xyz.mhmm.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ @ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }),
		@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) })
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoTests {

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private UserDAO userDAO;

	static Long userNo;

	@Test
	@Commit
	public void test1_createUser() {
		AuthDTO.Create user = createDto();
		userDAO.create(user);
		userNo = user.getNo();
		assertThat(user.getNo()).isNotNull();
	}

	@Test
	public void test2_createLogin() {
		AuthDTO.Create user = createDto();
		user.setNo(userNo);
		System.out.println(user.toString());
		loginDAO.create(user);
		assertThat(user.getNo()).isNotNull();
	}

	public void test3_selectExistIdTest() {
		boolean result = loginDAO.findExistById("userid");
		assertThat(result).isTrue();

		result = loginDAO.findExistById("jonghwaid");
		assertThat(result).isFalse();
	}

	public void test3_selectExistEmailTest() {
		boolean result = userDAO.findExistByEmail("dqwdwq");
		assertThat(result).isFalse();

		result = userDAO.findExistByEmail("mhmm@mhmm.xyz");
		assertThat(result).isTrue();
	}

	public void test4_updateToNameTest() {
		UserVO user = createUserDtoForUser();
		user.setName("수정한이름");
		user.setNo(1L);
		userDAO.updateToName(user);
	}

	public void test4_updateToEmailTest() {
		UserVO user = createUserDtoForUser();
		user.setEmail("update@mhmm.xyz");
		user.setNo(1L);
		userDAO.updateToEmail(user);
	}

	public void test4_updateToPwTest() {
		LoginVO user = createLoginDto();
		user.setPw("바꾼비밀번호");
		loginDAO.updateToPw(user);
	}

	public void test5_deleteUser() {
		userDAO.delete(1L);
	}

	public AuthDTO.Create createDto() {
		AuthDTO.Create user = new AuthDTO.Create();
		user.setId("userid");
		user.setPw("userpw");
		user.setEmail("21322mhmm@mhmm.xyz");
		user.setName("종화");
		return user;
	}

	public LoginVO createLoginDto() {
		LoginVO user = new LoginVO();
		user.setId("userid");
		user.setPw("userpw");
		user.setNo(33L);
		return user;
	}

	public UserVO createUserDtoForUser() {
		UserVO user = new UserVO();
		user.setEmail("21322mhmm@mhmm.xyz");
		user.setName("종화");
		return user;
	}

}