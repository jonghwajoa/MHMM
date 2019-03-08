package friend;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
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
	@Description("친구 생성 테스트 세션이 없으면 리다이렉트")
	public void createNoSessionFailTest() throws Exception {
		FriendDTO.Add dto = new FriendDTO.Add();
		dto.setId("user5");

		mockMvc.perform(post("/api/friend/").contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/auth/login"))
//		.andDo(print())
		;
	}
		
	
	@Test
	@Description("친구 생성 성공 테스트")
	public void createSuccessTest() throws Exception {
		FriendDTO.Add dto = new FriendDTO.Add();
		dto.setId("user4");
		
		mockMvc.perform(post("/api/friend/").contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(dto))
					.sessionAttr("userNo", 47L))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("user4"))
			.andDo(print())
		;
	}
	
	@Test
	@Description("친구 생성 테스트")
	public void createOwnSeflFailTest() throws Exception {
		FriendDTO.Add dto = new FriendDTO.Add();
		dto.setId("user2");
		
		ResultActions result = mockMvc
				.perform(post("/api/friend/").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)).sessionAttr("userNo", 46L))
				.andExpect(status().isBadRequest()).andDo(print());

		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.message").value("자기자신을 친구추가 할 수 없습니다."));
		result.andExpect(jsonPath("$.code").value("F003"));
		result.andExpect(jsonPath("$.errors").value(IsNull.nullValue()));
	}
	

	@Test
	@Description("친구 목록 불러오기")
	public void findAllTest() throws Exception{
		mockMvc.perform(get("/api/friend/").contentType(MediaType.APPLICATION_JSON)
					.sessionAttr("userNo", 4L))
				.andExpect(status().isOk())
		.andDo(print())
		;
	}
	
	@Test
	@Description("친구 삭제하기 입력값 누락시 실패")
	public void deleteInvalidFailTest() throws Exception{
		FriendDTO.Delete dto = new FriendDTO.Delete();
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/friend/").contentType(MediaType.APPLICATION_JSON)
					.sessionAttr("userNo", 4L)
					.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.message").value("Invalid Input Value"))
				.andExpect(jsonPath("$.code").value("C001"))
				.andExpect(jsonPath("$.errors[0].field").value("no"))
				.andExpect(jsonPath("$.errors[0].value").value("NotNull"))
				.andExpect(jsonPath("$.errors[0].reason").value("삭제할 유저를 반드시 입력해야 합니다."))
//		.andDo(print())
		;
	}
		
	@Test
	@Description("친구 삭제하기 입력값 누락시 실패")
	public void deletImpossibleNoFailTest() throws Exception{
			FriendDTO.Delete dto = new FriendDTO.Delete();
			dto.setNo(-1L);
			mockMvc.perform(MockMvcRequestBuilders.delete("/api/friend/").contentType(MediaType.APPLICATION_JSON)
					.sessionAttr("userNo", 4L)
					.content(objectMapper.writeValueAsString(dto)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.status").value(400))
			.andExpect(jsonPath("$.message").value("Invalid Input Value"))
			.andExpect(jsonPath("$.code").value("C001"))
			.andExpect(jsonPath("$.errors[0].field").value("no"))
			.andExpect(jsonPath("$.errors[0].value").value("Min"))
			.andExpect(jsonPath("$.errors[0].reason").value("회원 고유번호는 0 이상의 값이어야 합니다."))
		.andDo(print())
		;
	}
		
	@Test
	@Description("친구 삭제하기 성공")
	public void deleteSuccessTest() throws Exception{ 
		FriendDTO.Delete dto = new FriendDTO.Delete();
		dto.setNo(Long.MAX_VALUE);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/friend/").contentType(MediaType.APPLICATION_JSON)
				.sessionAttr("userNo", 4L)
				.content(objectMapper.writeValueAsString(dto)))
			.andExpect(status().isNoContent())
		.andDo(print())
		;
	}
	
	@Test
	@Description("친구 검색하기 존재하지 않을때")
	public void searchNotFoundTest() throws Exception{
		ResultActions result = mockMvc.perform(get("/api/friend/user12312").contentType(MediaType.APPLICATION_JSON)
				.sessionAttr("userNo", 46L))
			.andExpect(status().isNotFound())
//		.andDo(print())
		;
		
		result.andExpect(jsonPath("$.status").value(404));
		result.andExpect(jsonPath("$.message").value("존재하지 않는 유저 입니다."));
		result.andExpect(jsonPath("$.code").value("F004"));
		result.andExpect(jsonPath("$.errors").value(IsNull.nullValue()));
		
	}
	

	@Test
	@Description("친구 검색 성공시")
	public void searchSuccessTest() throws Exception{
		mockMvc.perform(get("/api/friend/user1").contentType(MediaType.APPLICATION_JSON)
				.sessionAttr("userNo", 46L))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.no").value(45))
			.andExpect(jsonPath("$.id").value("user1"))
			.andExpect(jsonPath("$.name").value("종화"))
//		.andDo(print())
		;
		
	}
	
	@Test
	@Description("아이디 검색시 아이디가 5자리 이하일때") 
	public void searchUnderLenthTest() throws Exception{
		ResultActions result = mockMvc.perform(get("/api/friend/us1").contentType(MediaType.APPLICATION_JSON)
				.sessionAttr("userNo", 46L))
			.andExpect(status().isBadRequest())
		.andDo(print())
		;
		
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.code").value("C001"));
		result.andExpect(jsonPath("$.errors[0].field").value("id"));
		result.andExpect(jsonPath("$.errors[0].value").value("Length"));
		result.andExpect(jsonPath("$.errors[0].reason").value("아이디는 최소 5자리 이상입니다."));
	}
		
	@Test
	@Description("아이디 검색시 아이디가 20자리 이상일때")
	public void searchOverLenthTest() throws Exception{
		ResultActions result = mockMvc.perform(get("/api/friend/user1d12d12d21d21d12d12d1").contentType(MediaType.APPLICATION_JSON)
				.sessionAttr("userNo", 46L))
			.andExpect(status().isBadRequest())
		.andDo(print())
		;
		
	
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.code").value("C001"));
		result.andExpect(jsonPath("$.errors[0].field").value("id"));
		result.andExpect(jsonPath("$.errors[0].value").value("Length"));
		result.andExpect(jsonPath("$.errors[0].reason").value("아이디는 최대 20자리 입니다."));
		
	}
}
