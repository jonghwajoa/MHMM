package friend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;
import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;
import xyz.mhmm.friend.FriendDTO;
import xyz.mhmm.friend.FriendService;
import xyz.mhmm.friend.exception.AlreadyFriendException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class })
@WebAppConfiguration
@Transactional
public class FriendServiceTests {

	@Autowired
	private FriendService service;

	@Test
	@Description("친구 추가 테스트")
	public void createTest() {
		FriendDTO.Add dto = new FriendDTO.Add();
		dto.setId("user1");
		try {
			service.create(4L, dto);
		} catch (BusinessException e) {
			assertThat(e).isInstanceOf(AlreadyFriendException.class).hasMessage("이미 친구등록한 유저입니다.");
			ErrorCode code = e.getErrorCode();
			assertThat(code.getCode()).isEqualTo("F002");
			assertThat(code.getStatus()).isEqualTo(400);
		}
	}

}
