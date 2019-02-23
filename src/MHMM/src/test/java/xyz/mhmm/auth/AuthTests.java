package xyz.mhmm.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.activation.MimeType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import xyz.mhmm.controller.AuthController;

@RunWith(SpringJUnit4ClassRunner.class)
// spring-test에서 제공하는 단위 테스트를 위한 클래스 러너
@ContextHierarchy({ @ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }),
		@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) })
// 테스트의 설정이 들어있는 xml의 위치
@WebAppConfiguration
public class AuthTests {

	@Autowired
	AuthController authController;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
	}

	@Test
	public void getLoginTest() throws Exception {
		mockMvc.perform(get("/auth/login")).andDo(print()).andExpect(status().isOk());
		System.out.println("-----------------------");
	}

	public void postLoginTest() throws Exception {
		mockMvc.perform(post("/auth/login")).andDo(print()).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));
	}
}
