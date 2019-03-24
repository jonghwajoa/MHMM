package message;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.messenge.MessageDTO;
import xyz.mhmm.messenge.MessageType;
import xyz.mhmm.messenge.dao.MessageDAO;
import xyz.mhmm.messenge.domain.MessageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, DBConfig.class })
@WebAppConfiguration
@Transactional
public class MessageDAOTest {

	@Autowired
	private MessageDAO dao;

	@Test
	public void createTest() {
		MessageDTO dto = new MessageDTO();
		dto.setChatroom_no(1L);
		dto.setCreated_at(new SimpleDateFormat("yyyy-MM-dd HH시 mm분").format(new java.util.Date()));
		dto.setMessage("헤헤");
		dto.setType(MessageType.CHAT);
		dto.setUser_no(44L);
		dto.setUser_name("아이디");

		dao.create(dto);
	}

	@Test
	public void findAllTest() {

		List<MessageVO> vo = dao.findAll(24L);

		for (MessageVO e : vo) {
			System.out.println(e.toString());
		}
	}
}
