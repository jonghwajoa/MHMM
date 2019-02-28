package auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

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
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.mhmm.controller.AuthController;
import xyz.mhmm.dto.AuthDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ @ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }),
		@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) })
@WebAppConfiguration
public class AuthControllerTests {

	@Autowired
	private AuthController authController;

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


	@Description("이메일이 양식이 아닌경우")
	public void SignupInvalidInputTest() throws Exception {
		AuthDTO.Create createDTO = new AuthDTO.Create();
		createDTO.setId("useridg");
		createDTO.setPw("jonghwapw");
		createDTO.setName("name");
		createDTO.setEmail("mhmmmhmm.xyz");
		
		ResultActions result = mockMvc
				.perform(post("/auth/signup")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(createDTO)))
				.andDo(print())
				.andExpect(status().isBadRequest());
		
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
		createDTO.setName("name");
		createDTO.setEmail("mhmm@mhmm.xyz");
		
		ResultActions result = mockMvc
				.perform(post("/auth/signup")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(createDTO)))
				.andDo(print())
				.andExpect(status().isBadRequest());

		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.code").value("C001"));
		result.andExpect(jsonPath("$.errors[0].field").value("id"));
		result.andExpect(jsonPath("$.errors[0].value").value("Length"));
		result.andExpect(jsonPath("$.errors[0].reason").value("아이디는 최소 5자리 이상입니다."));
	}

	@Description("존재하는 이메일인 경우")
	public void SignupDuplicatedEmialTest() throws Exception {
		AuthDTO.Create createDTO = new AuthDTO.Create();
		createDTO.setId("useridg");
		createDTO.setPw("jonghwapw");
		createDTO.setName("name");
		createDTO.setEmail("mhmm@mhmm.xyz");
		
		ResultActions result = mockMvc
				.perform(post("/auth/signup")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(createDTO)))
				.andDo(print())
				.andExpect(status().isBadRequest());

		result.andExpect(jsonPath("$.message").value("이미 사용중인 이메일 입니다. 다른 이메일을 사용해주세요."));
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.code").value("A001"));
		result.andExpect(jsonPath("$.errors").value(IsNull.nullValue()));
	}

	public MultiValueMap<String, String> getParams() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("id", "useridg");
		map.add("pw", "jonghwapw");
		map.add("email", "mhmm@mhmm.xyz");
		map.add("name", "name");
		return map;
	}
}
