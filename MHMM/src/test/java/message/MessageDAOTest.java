package message;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.messenger.MessageDTO;
import xyz.mhmm.messenger.MessageType;
import xyz.mhmm.messenger.dao.MessageDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, DBConfig.class })
@WebAppConfiguration
@Transactional
public class MessageDAOTest {

	@Autowired
	MessageDAO dao;

	@Test
	public void createTest() {
		MessageDTO dto = new MessageDTO();
		dto.setChatroom_no(1L);
		dto.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH시 mm분").format(new java.util.Date()));
		dto.setMessage("헤헤");
		dto.setType(MessageType.CHAT);
		dto.setUser_no(44L);
		dto.setUser_id("아이디");

		dao.create(dto);
	}
}
