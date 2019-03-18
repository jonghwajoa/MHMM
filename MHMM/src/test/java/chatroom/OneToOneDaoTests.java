package chatroom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.chatRoom.dao.OneToOneDAO;
import xyz.mhmm.chatRoom.domain.OneToOneVO;
import xyz.mhmm.chatRoom.dto.OneToOneDTO;
import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, DBConfig.class, WebConfig.class })
@WebAppConfiguration
@Transactional
public class OneToOneDaoTests {

	@Autowired
	OneToOneDAO dao;

	@Test
	public void insertTest() {
		OneToOneDTO.create dto = new OneToOneDTO.create();
		dto.setTo_userno(2L);
		dto.setFrom_userno(3L);
		dao.createFromTo(dto);
		dao.createToFrom(dto);
		System.out.println(dto.toString());
	}

	@Test
	public void selectTest() {
		OneToOneDTO.create dto = new OneToOneDTO.create();
		dto.setTo_userno(1L);
		dto.setFrom_userno(2L);
		OneToOneVO result = dao.selectFromTo(dto);
		System.out.println(result.toString());

		result = dao.selectToFrom(dto);
		System.out.println(result.toString());
	}
}
