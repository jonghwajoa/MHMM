package auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;
import org.thymeleaf.engine.IElementDefinitionsAware;

import xyz.mhmm.controller.AuthController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ @ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }),
		@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) })
@WebAppConfiguration
public class ControllerTests {

	@Autowired
	AuthController authController;

	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext ctx;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	public void getLoginTest() throws Exception {
		mockMvc.perform(get("/auth/login")).andDo(print()).andExpect(status().isOk());
		System.out.println("-----------------------");
	}

	public void postLoginTest() throws Exception {
		mockMvc.perform(post("/auth/login")).andDo(print()).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));
	}

	@Test
	@Description("이메일이 양식이 앙닌경우")
	public void postSignupTest() throws Exception {
		MultiValueMap<String, String> map = getParams();

		MvcResult result = mockMvc.perform(post("/auth/signup").params(map)).andExpect(status().isOk()).andReturn();
//				.andExpect(model().hasErrors()).andReturn();


	}

	public MultiValueMap<String, String> getParams() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("id", "d");
		map.add("pw", "jonghwapw");
		map.add("email", "eamilxyz");
		map.add("name", "name");
		return map;
	}
}
