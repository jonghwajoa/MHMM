package mypage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;
import xyz.mhmm.mypage.MyPageDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class, DBConfig.class })
@WebAppConfiguration
@Transactional
public class MyPageControllerTest {
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
	public void passwordChnageSuccessTest() throws Exception{
		MyPageDTO.PasswordChange dto = new MyPageDTO.PasswordChange();
		dto.setCurPW("user10");
		dto.setChangePwCheck("user10");
		dto.setChangePw("user10");
		
		mockMvc.perform(patch("/api/mypage/password")
					.sessionAttr("userNo", 63L)
					.content(objectMapper.writeValueAsString(dto))
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isNoContent())
		;
	}
	
	@Test
	@Description("curpw 누락시")
	public void passwordChnageFailTest() throws Exception{
		MyPageDTO.PasswordChange dto = new MyPageDTO.PasswordChange();
		dto.setChangePwCheck("user10");
		dto.setChangePw("user10");
		
		ResultActions result = mockMvc.perform(patch("/api/mypage/password")
					.sessionAttr("userNo", 63L)
					.content(objectMapper.writeValueAsString(dto))
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isBadRequest())
			
		;
		
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.code").value("COMMON001"));
		result.andExpect(jsonPath("$.errors[0].reason").value("현재 비밀번호는 반드시 입력해야 합니다."));
	}
	
	@Test
	@Description("setChangePw 누락시")
	public void passwordChnageFailTest2() throws Exception{
		MyPageDTO.PasswordChange dto = new MyPageDTO.PasswordChange();
		dto.setCurPW("user10");
		dto.setChangePwCheck("user10");
		
		ResultActions result = mockMvc.perform(patch("/api/mypage/password")
					.sessionAttr("userNo", 63L)
					.content(objectMapper.writeValueAsString(dto))
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isBadRequest())
			
		;
		
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.code").value("COMMON001"));
		result.andExpect(jsonPath("$.errors[0].reason").value("변경할 비밀번호는 반드시 입력해야 합니다."));
	}
	
	@Test
	@Description("setChangePwCheck 누락시")
	public void passwordChnageFailTest3() throws Exception{
		MyPageDTO.PasswordChange dto = new MyPageDTO.PasswordChange();
		dto.setCurPW("user10");
		dto.setChangePw("user10");
		
		ResultActions result = mockMvc.perform(patch("/api/mypage/password")
					.sessionAttr("userNo", 63L)
					.content(objectMapper.writeValueAsString(dto))
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isBadRequest())
			
		;
		
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.code").value("COMMON001"));
		result.andExpect(jsonPath("$.errors[0].reason").value("변경할 비밀번호의 확인비밀번호는 반드시 입력해야 합니다."));
	}
	
	@Test
	@Description("setChangePwCheck 가 다를경우")
	public void passwordChnageFailTest4() throws Exception{
		MyPageDTO.PasswordChange dto = new MyPageDTO.PasswordChange();
		dto.setCurPW("user10");
		dto.setChangePw("user10");
		dto.setChangePwCheck("user1011111");
		
		ResultActions result = mockMvc.perform(patch("/api/mypage/password")
					.sessionAttr("userNo", 63L)
					.content(objectMapper.writeValueAsString(dto))
					.contentType(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isBadRequest())
			
		;
		
		result.andExpect(jsonPath("$.status").value(400));
		result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
		result.andExpect(jsonPath("$.code").value("COMMON001"));
		result.andExpect(jsonPath("$.errors[0].reason").value("변경할 비밀번호가 확인과 일치하지 않습니다."));
	}

}
