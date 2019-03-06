package friend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
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
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;
import xyz.mhmm.friend.FriendDTO;
import xyz.mhmm.friend.FriendRestController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class, DBConfig.class })
@WebAppConfiguration
@Transactional
public class FriendRestControllerTests {

	@Autowired
	FriendRestController controller;

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
	@Description("유저 생성 테스트")
	public void createTest() throws Exception{
		FriendDTO.Add dto = new FriendDTO.Add();
		dto.setId("user10");
		
		mockMvc.perform(post("/api/friend/").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/auth/login"))
//		.andDo(print())
		;
		
		
		HashMap<String, Object> session = new HashMap<String, Object>();
		session.put("userNo", 5L);
		
		mockMvc.perform(post("/api/friend/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto))
				.sessionAttr("userNo", 5L))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value("user10"))
//			.andDo(print())
			;

		dto.setId("user1");
		ResultActions result = mockMvc.perform(post("/api/friend/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(dto))
					.sessionAttr("userNo", 5L))
				.andExpect(status().isBadRequest())
				.andDo(print())
				;
		
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.message").value("자기자신을 친구추가 할 수 없습니다."));
		result.andExpect(jsonPath("$.code").value("F003"));
		result.andExpect(jsonPath("$.errors").value(IsNull.nullValue()));
		
	}

}
