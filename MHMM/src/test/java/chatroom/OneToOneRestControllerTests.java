package chatroom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.mhmm.chatRoom.dto.OneToOneDTO;
import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class, DBConfig.class })
@WebAppConfiguration
@Transactional
public class OneToOneRestControllerTests {

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
	public void findAllTest() throws Exception {
		mockMvc.perform(get("/api/messenger/chatroom/").sessionAttr("userNo", 47L)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void createOrFindTest() throws Exception {
		OneToOneDTO.FindAndCreate dto = new OneToOneDTO.FindAndCreate();
		dto.setTo_userno(1L);

		mockMvc.perform(post("/api/messenger/chatroom/")
					.sessionAttr("userNo", 47L)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(dto))
				)
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.no").exists())
		;
	}

}
