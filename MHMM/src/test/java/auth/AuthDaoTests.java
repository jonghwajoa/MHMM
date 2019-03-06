package auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.auth.AuthDTO;
import xyz.mhmm.auth.dao.LoginDAO;
import xyz.mhmm.auth.dao.UserDAO;
import xyz.mhmm.auth.domain.LoginVO;
import xyz.mhmm.auth.domain.UserVO;
import xyz.mhmm.config.WebApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebApplication.class)
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthDaoTests {

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private UserDAO userDAO;

	static Long userNo;

	@Test
	public void test1_createUser() {
		AuthDTO.Create user = createDto();
		userDAO.create(user);
		userNo = user.getNo();
		assertThat(user.getNo()).isNotNull();
	}

	@Test
	public void test2_createLogin() {
		AuthDTO.Create user = createDto();
		user.setPwCheck("userpw");
		user.setNo(userNo);
		System.out.println(user.toString());
		loginDAO.create(user);
		assertThat(user.getNo()).isNotNull();
	}

	@Test
	public void findByEamilTest() {
		UserVO vo = userDAO.findByEmail("mhmm@mhmm.xyz");
		System.out.println("응???");
		System.out.println(vo.toString());
	}

	public void updateToNameTest() {
		UserVO user = createUserDtoForUser();
		user.setNo(1L);
		userDAO.updateToName(user);
	}

	public void updateToEmailTest() {
		UserVO user = createUserDtoForUser();
		user.setNo(1L);
		userDAO.updateToEmail(user);
	}

	public void test4_updateToPwTest() {
		LoginVO user = createLoginDto();
		user.setPw("바꾼비밀번호");
		loginDAO.updateToPw(user);
	}

	public void deleteUser() {
		userDAO.delete(1L);
	}

	public AuthDTO.Create createDto() {
		AuthDTO.Create user = new AuthDTO.Create();
		user.setId("userid");
		user.setPw("userpw");
		user.setEmail("mhmm@mhmm.xyz");
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
		return user;
	}

}