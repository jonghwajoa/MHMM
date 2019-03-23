package message;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class, DBConfig.class })
@WebAppConfiguration
@Transactional
public class MessageRestControllerTest {

	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext ctx;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	@Description("성공 케이스")
	public void OneToOneChatFindAllTest() throws Exception {
		mockMvc.perform(get("/api/message/onetoone/24")
				.sessionAttr("userNo", 45L)).andDo(print())
				.andExpect(status().isOk())
		;
	}
	
	@Test
	@Description("404 실패 케이스")
	public void OneToOneChatFindAllTest2() throws Exception {
		mockMvc.perform(get("/api/message/onetoone/11")
				.sessionAttr("userNo", 45L)).andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.message").value("접근할 수 없는 채팅방입니다."))
				.andExpect(jsonPath("$.code").value("M002"))
		;
	}
	
	@Test
	@Description("403 실패 케이스")
	public void OneToOneChatFindAllTest3() throws Exception {
		mockMvc.perform(get("/api/message/onetoone/24")
				.sessionAttr("userNo", 1L))
			.andDo(print())
			.andExpect(status().isForbidden())
			.andExpect(jsonPath("$.status").value(403))
			.andExpect(jsonPath("$.message").value("접근할 수 없는 채팅방입니다."))
			.andExpect(jsonPath("$.code").value("M001"))
		;
	}

}
