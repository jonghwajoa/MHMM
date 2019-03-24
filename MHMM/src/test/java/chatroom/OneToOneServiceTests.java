package chatroom;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.chatRoom.OneToOneService;
import xyz.mhmm.chatRoom.domain.OneToOneVO;
import xyz.mhmm.chatRoom.dto.OneToOneDTO;
import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class, DBConfig.class })
@WebAppConfiguration
@Transactional
public class OneToOneServiceTests {

	@Autowired
	private OneToOneService oneToOneService;

	@Test
	public void createTest() {	
		OneToOneDTO.FindAndCreate dto = new OneToOneDTO.FindAndCreate();
		dto.setFrom_userno(2L);
		dto.setTo_userno(3L);

		Long no = oneToOneService.create(dto);
		assertThat(no).isNotNull();

		OneToOneDTO.FindAndCreate dto2 = new OneToOneDTO.FindAndCreate();
		dto2.setFrom_userno(999L);
		dto2.setTo_userno(3L);
		oneToOneService.create(dto2);

	}

	@Test
	public void findAllTest() {
		List<OneToOneVO.findAllVO> list = oneToOneService.findAll(3L);

		System.out.println(list.toString());
	}

}
