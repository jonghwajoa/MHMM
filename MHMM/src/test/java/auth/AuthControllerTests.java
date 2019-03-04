package auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.mhmm.auth.AuthRestController;
import xyz.mhmm.auth.AuthDTO;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class })
@WebAppConfiguration
@Transactional
public class AuthControllerTests {

	@Autowired
	private AuthRestController authController;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext ctx;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		objectMapper = new ObjectMapper();
	}

	public void getLoginTest() throws Exception {
		mockMvc.perform(get("/auth/login")).andDo(print()).andExpect(status().isOk());
		System.out.println("-----------------------");
	}

	@Test
	@Description("이메일이 양식이 아닌경우")
	public void SignupInvalidInputTest() throws Exception {
		AuthDTO.Create createDTO = new AuthDTO.Create();
		createDTO.setId("useridg");
		createDTO.setPw("jonghwapw");
		createDTO.setPwCheck("jonghwapw");
		createDTO.setName("name");
		createDTO.setEmail("mhmmmhmm.xyz");

		ResultActions result = mockMvc.perform(post("/auth/signup").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createDTO))).andExpect(status().isBadRequest());

		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.code").value("C001"));
		result.andExpect(jsonPath("$.errors[0].field").value("email"));
		result.andExpect(jsonPath("$.errors[0].value").value("Email"));
		result.andExpect(jsonPath("$.errors[0].reason").value("이메일 형식이 올바르지 않습니다."));
	}

	@Test
	@Description("아이디가 짧은경우...")
	public void SignupInvalidInputTest2() throws Exception {
		AuthDTO.Create createDTO = new AuthDTO.Create();
		createDTO.setId("dg");
		createDTO.setPw("jonghwapw");
		createDTO.setPwCheck("jonghwapw");
		createDTO.setName("name");
		createDTO.setEmail("mhmm@mhmm.xyz");

		ResultActions result = mockMvc
				.perform(post("/auth/signup").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(createDTO)))
				.andExpect(status().isBadRequest()).andDo(print());

		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.code").value("C001"));
		result.andExpect(jsonPath("$.errors[0].field").value("id"));
		result.andExpect(jsonPath("$.errors[0].value").value("Length"));
		result.andExpect(jsonPath("$.errors[0].reason").value("아이디는 최소 5자리 이상입니다."));
	}

	@Test
	@Description("비밀번호 확인이 일치하지 않는 경우")
	public void SignupNotEqPwTest() throws Exception {
		AuthDTO.Create createDTO = new AuthDTO.Create();
		createDTO.setId("userid");
		createDTO.setPw("jonghwapw");
		createDTO.setPwCheck("jonghaawapw");
		createDTO.setName("name");
		createDTO.setEmail("mhmm@amhmm.xyz");

		ResultActions result = mockMvc.perform(post("/auth/signup").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createDTO))).andExpect(status().isBadRequest());

		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.code").value("C001"));
		result.andExpect(jsonPath("$.errors[0].field").value("pw"));
		result.andExpect(jsonPath("$.errors[0].value").value("Not Equal"));
		result.andExpect(jsonPath("$.errors[0].reason").value("비밀번호 입력값이 올바르지 않습니다."));

	}

	@Test
	@Description("존재하는 이메일인 경우")
	public void SignupDuplicatedEmialTest() throws Exception {
		AuthDTO.Create createDTO = new AuthDTO.Create();
		createDTO.setId("useridg");
		createDTO.setPw("jonghwapw");
		createDTO.setPwCheck("jonghwapw");
		createDTO.setName("name");
		createDTO.setEmail("mhmm@mhmm.xyz");

		ResultActions result = mockMvc.perform(post("/auth/signup").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createDTO))).andExpect(status().isBadRequest());

		result.andExpect(jsonPath("$.message").value("이미 사용중인 이메일 입니다. 다른 이메일을 사용해주세요."));
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.code").value("A001"));
		result.andExpect(jsonPath("$.errors").value(IsNull.nullValue()));
	}

	@Test
	@Description("지원히지 않는 자료타입인경우")
	public void SignupInvalidMimeTypeTest() throws Exception {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("id", "useridg");
		map.add("pw", "jonghwapw");
		map.add("checkPw", "jonghwapw");
		map.add("email", "mhmm@mhmm.xyz");
		map.add("name", "name");

		ResultActions result = mockMvc.perform(post("/auth/signup").params(map))
				.andExpect(status().isUnsupportedMediaType());

		result.andDo(print());
	}

	@Test
	@Description("로그인 성공시 200과 ID를 응답한다.")
	public void login() throws Exception {
		AuthDTO.Login loginDTO = new AuthDTO.Login();
		loginDTO.setId("userid");
		loginDTO.setPw("userpw");

		mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(loginDTO))).andDo(print())
				.andExpect(jsonPath("$.id").value("userid")).andExpect(jsonPath("$.pw").doesNotExist())
				.andExpect(status().isOk());
	}

	@Test
	@Description("로그인 Invalid Input")
	public void loginInvalidInputTest() throws Exception {
		AuthDTO.Login loginDTO = new AuthDTO.Login();
		loginDTO.setId("uid");
		loginDTO.setPw("upw");

		ResultActions result = mockMvc
				.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(loginDTO)))
				.andDo(print()).andExpect(status().isBadRequest());

		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.code").value("C001"));
		
	
		result.andExpect(jsonPath("$.errors[0].field").value("id"));
		result.andExpect(jsonPath("$.errors[0].value").value("Length"));
		result.andExpect(jsonPath("$.errors[0].reason").value("아이디는 최소 5자리 이상입니다."));
		result.andExpect(jsonPath("$.errors[1].field").value("pw"));
		result.andExpect(jsonPath("$.errors[1].value").value("Length"));
		result.andExpect(jsonPath("$.errors[1].reason").value("비밀번호는 최소 5자리 이상입니다."));
	}

	@Test
	@Description("로그인시 없는 ID인경우...")
	public void LoginNotExistIdTest() throws Exception {
		AuthDTO.Login loginDTO = new AuthDTO.Login();
		loginDTO.setId("useriddidid");
		loginDTO.setPw("upwpwpw");

		ResultActions result = mockMvc
				.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(objectMapper.writeValueAsString(loginDTO)))
				.andDo(print())
				.andExpect(status().isNotFound());

		result.andExpect(jsonPath("$.status").value(404));
		result.andExpect(jsonPath("$.message").value("아이디 혹은 비밀번호가 올바르지 않습니다."));
		result.andExpect(jsonPath("$.code").value("A003"));
		result.andExpect(jsonPath("$.errors").value(IsNull.nullValue()));

	}

}
