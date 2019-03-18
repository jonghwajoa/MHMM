package chatroom;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.chatRoom.OneToOneService;
import xyz.mhmm.chatRoom.dto.OneToOneDTO;
import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class, DBConfig.class })
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OneToOneServiceTests {

	@Autowired
	private OneToOneService oneToOneService;

	@Test
	public void createTest() {
		OneToOneDTO.create dto = new OneToOneDTO.create();
		dto.setFrom_userno(2L);
		dto.setTo_userno(3L);

		oneToOneService.create(dto);
		assertThat(dto.getNo()).isNotNull();
	}
}
