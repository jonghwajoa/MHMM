package xyz.mhmm.auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.domain.UserDTO;
import xyz.mhmm.errors.UserDuplicatedException;
import xyz.mhmm.service.AuthService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ @ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }),
		@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) })
@WebAppConfiguration
@Transactional
public class ServiceTests {

	@Autowired
	AuthService authService;

	@Test
	public void test1_SingupIdExceptionTest() {
		UserDTO user = createUserDto();
		UserDTO result = null;
		try {
			result = authService.create(user);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(UserDuplicatedException.class).hasMessage("존재하는 아이디 입니다. 다른 아이디를 입력해 주세요.");
		}
		assertThat(result).isNull();
	}

	@Test
	public void test1_SingupEmailExceptionTest() {
		UserDTO user = createUserDto();
		user.setId("updateId");
		UserDTO result = null;
		try {
			result = authService.create(user);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(UserDuplicatedException.class).hasMessage("존재하는 이메일 입니다. 다른 이메를 입력해 주세요.");
		}
		assertThat(result).isNull();
	}

	public UserDTO createUserDto() {
		UserDTO user = new UserDTO();
		user.setEmail("mhmm@mhmm.xyz");
		user.setName("종화");
		user.setId("userid");
		user.setPw("userpw");
		return user;
	}

}
