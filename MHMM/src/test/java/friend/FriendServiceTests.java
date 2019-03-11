package friend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.auth.domain.UserVO;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;
import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.exception.ErrorCode;
import xyz.mhmm.friend.FriendDTO;
import xyz.mhmm.friend.FriendService;
import xyz.mhmm.friend.domain.FriendVO;
import xyz.mhmm.friend.exception.AlreadyFriendException;
import xyz.mhmm.friend.exception.SearchNotFound;

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
		dto.setId("user2");
		try {
			service.create(45L, dto);
		} catch (BusinessException e) {
			assertThat(e).isInstanceOf(AlreadyFriendException.class).hasMessage("이미 친구등록한 유저입니다.");
			ErrorCode code = e.getErrorCode();
			assertThat(code.getCode()).isEqualTo("F002");
			assertThat(code.getStatus()).isEqualTo(400);
		}
	}

	@Test
	@Description("모든 친구 불러오기")
	public void findAllTest() {
		List<FriendVO.list> list = service.findAll(4L);
		for (FriendVO.list e : list) {
			assertThat(e.getName()).isNotNull();
			assertThat(e.getNo()).isNotNull();
		}
	}

	@Test
	@Description("친구 삭제")
	public void deleteTest() {
		FriendDTO.Delete dto = new FriendDTO.Delete();
		dto.setNo(12L);
		service.delete(1L, dto);
	}

	@Test
	@Description("친구 검색")
	public void search() {
		FriendDTO.Search dto = new FriendDTO.Search();
		dto.setId("user2");
		UserVO result = service.search(45L, dto);
		assertThat(result).hasFieldOrProperty("no");
		assertThat(result).hasFieldOrProperty("name");
		assertThat(result).hasFieldOrProperty("email");
		assertThat(result).hasFieldOrProperty("id");

		dto.setId("user22222");
		try {
			result = service.search(45L, dto);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(SearchNotFound.class).hasMessage("존재하지 않는 유저 입니다.");
		}
	}

}
