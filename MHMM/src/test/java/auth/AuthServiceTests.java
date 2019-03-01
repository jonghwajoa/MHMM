package auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.commons.BusinessException;
import xyz.mhmm.commons.EmailDuplicatedException;
import xyz.mhmm.commons.ErrorCode;
import xyz.mhmm.commons.UserNotExistException;
import xyz.mhmm.domain.UserVO;
import xyz.mhmm.dto.AuthDTO;
import xyz.mhmm.dto.AuthDTO.Login;
import xyz.mhmm.persistence.LoginDAO;
import xyz.mhmm.service.AuthService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ @ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }),
		@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) })
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthServiceTests {

	@Autowired
	private AuthService authService;

	@Description("이미 사용중인 아이디 일경우")
	public void test1_SingupIdExceptionTest() {
		AuthDTO.Create user = createDto();
		AuthDTO.Create result = null;

		try {
			result = authService.create(user);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(EmailDuplicatedException.class).hasMessage("이미 사용중인 아이디 입니다. 다른 아이디를 사용해주세요.");
		}
		assertThat(result).isNull();
	}

	@Description("이미 사용중인 이메일인 경우")
	public void test1_SingupEmailExceptionTest() {
		AuthDTO.Create user = createDto();
		user.setId("updateId");
		AuthDTO.Create result = null;
		try {
			result = authService.create(user);
		} catch (BusinessException e) {
			assertThat(e).isInstanceOf(EmailDuplicatedException.class).hasMessage("이미 사용중인 이메일 입니다. 다른 이메이을 사용해주세요.");
		}
		assertThat(result).isNull();
	}

	@Description("회원 가입 성공한 경우")
	public void test1_SingupSuccessTest() {
		AuthDTO.Create user = createDto();
		user.setId("updateId");
		user.setEmail("updateEmail@mhmm.xyz");
		AuthDTO.Create result = null;
		try {
			result = authService.create(user);
		} catch (Exception e) {
			System.out.println(e);
		}
		assertThat(result).hasFieldOrPropertyWithValue("email", "updateEmail@mhmm.xyz")
				.hasFieldOrPropertyWithValue("name", "jonghwa").hasFieldOrPropertyWithValue("id", "updateId")
				.hasFieldOrProperty("no");
	}

	@Description("로그인 실패하는경우")
	public void test2_LoginFailTest() {
		AuthDTO.Login user = new AuthDTO.Login();

		user.setId("userid");
		user.setPw("userpwpw");
		try {
			authService.Login(user);
		} catch (BusinessException e) {
			ErrorCode exception = e.getErrorCode();
			assertThat(exception.getMessage()).isEqualTo("아이디 혹은 비밀번호가 올바르지 않습니다.");
			assertThat(exception.getStatus()).isEqualTo(404);
			assertThat(exception.getCode()).isEqualTo("A003");
		}
	}

	@Test
	@Description("로그인 성공하는 경우")
	public void test2_LoginSuccessTest() {
		AuthDTO.Login user = new AuthDTO.Login();
		user.setId("userid");
		user.setPw("userpw");
		try {
			Login result = authService.Login(user);
			assertThat(result.getId()).isEqualTo(user.getId());
			assertThat(result.getPw()).isEqualTo(user.getPw());
		} catch (BusinessException e) {
			System.out.println(e);
		}

	}

	public AuthDTO.Create createDto() {
		AuthDTO.Create user = new AuthDTO.Create();
		user.setEmail("mhmm@mhmm.xyz");
		user.setName("jonghwa");
		user.setId("userid");
		user.setPw("userpw");
		return user;
	}

}