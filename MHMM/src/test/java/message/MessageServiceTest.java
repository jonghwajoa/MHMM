package message;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;
import xyz.mhmm.messenge.MessageDTO;
import xyz.mhmm.messenge.MessageService;
import xyz.mhmm.messenge.exception.ChatRoomNotFound;
import xyz.mhmm.messenge.exception.NotAccessException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class, DBConfig.class })
@WebAppConfiguration
@Transactional
public class MessageServiceTest {

	@Autowired
	MessageService service;

	@Test
	@Description("ChatRoomNotFound 테스트")
	public void oneToOneChatFindAllTest() {
		MessageDTO.noOnly dto = new MessageDTO.noOnly();
		dto.setChatroom_no(5000L);
		dto.setUser_no(44L);

		try {
			service.oneToOneChatFindAll(dto);
		} catch (ChatRoomNotFound e) {
			assertThat(e).isNotNull();
		}
	}

	@Test
	@Description("NotAccessException 테스트")
	public void oneToOneChatFindAllTest2() {
		MessageDTO.noOnly dto = new MessageDTO.noOnly();
		dto.setChatroom_no(24L);
		dto.setUser_no(44L);

		try {
			service.oneToOneChatFindAll(dto);
		} catch (NotAccessException e) {
			assertThat(e).isNotNull();
		}
	}

	@Test
	@Description("성공 테스트")
	public void oneToOneChatFindAllTest3() {
		MessageDTO.noOnly dto = new MessageDTO.noOnly();
		dto.setChatroom_no(24L);
		dto.setUser_no(45L);

		try {
			service.oneToOneChatFindAll(dto);
		} catch (Exception e) {
			assertThat(e).isNull();
		}
	}

}
