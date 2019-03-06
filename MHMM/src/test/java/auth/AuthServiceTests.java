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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.auth.AuthDTO;
import xyz.mhmm.auth.AuthDTO.Login;
import xyz.mhmm.auth.exception.EmailDuplicatedException;
import xyz.mhmm.auth.exception.IdDuplicatedException;
import xyz.mhmm.auth.AuthService;
import xyz.mhmm.auth.domain.LoginVO;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;
import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class })
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthServiceTests {

	@Autowired
	private AuthService authService;

	@Test
	@Description("이미 사용중인 아이디 일경우")
	public void test1_SingupIdExceptionTest() {
		AuthDTO.Create user = new AuthDTO.Create();
		user.setEmail("mhmm321@mhmm.xyz");
		user.setName("jonghwa");
		user.setId("user1");
		user.setPw("userpw");
		user.setPwCheck("userpw");
		AuthDTO.Create result = null;

		try {
			result = authService.create(user);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(IdDuplicatedException.class).hasMessage("이미 사용중인 아이디 입니다. 다른 아이디를 사용해주세요.");
		}
		assertThat(result).isNull();
	}

	@Test
	@Description("이미 사용중인 이메일인 경우")
	public void test1_SingupEmailExceptionTest() {
		AuthDTO.Create user = new AuthDTO.Create();
		user.setEmail("mhmm@mhmm.xyz");
		user.setName("jonghwa");
		user.setId("user12312");
		user.setPw("userpw");
		user.setPwCheck("userpw");
		AuthDTO.Create result = null;
		
		try {
			result = authService.create(user);
		} catch (BusinessException e) {
			assertThat(e).isInstanceOf(EmailDuplicatedException.class).hasMessage("이미 사용중인 이메일 입니다. 다른 이메일을 사용해주세요.");
		}
		assertThat(result).isNull();
	}

	@Test
	@Description("회원 가입 성공한 경우")
	public void test1_SingupSuccessTest() {
		AuthDTO.Create user = new AuthDTO.Create();
		user.setEmail("mhmm1111@mhmm.xyz");
		user.setName("jong1111");
		user.setId("user111");
		user.setPw("userpw");
		user.setPwCheck("userpw");
		AuthDTO.Create result = null;
		
		try {
			result = authService.create(user);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		assertThat(result)
				.hasFieldOrPropertyWithValue("email", "mhmm1111@mhmm.xyz")
				.hasFieldOrPropertyWithValue("name", "jong1111")
				.hasFieldOrPropertyWithValue("id", "user111")
				.hasFieldOrProperty("no");
	}

	@Test
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

	@Description("로그인 성공하는 경우")
	public void test2_LoginSuccessTest() {
		AuthDTO.Login user = new AuthDTO.Login();
		user.setId("user1");
		user.setPw("user1");
		try {
			LoginVO result = authService.Login(user);
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
