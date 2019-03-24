package chatroom;

import java.util.List;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, DBConfig.class })
@WebAppConfiguration
@Transactional
public class OneToOneDaoTests {

	@Autowired
	OneToOneDAO dao;

	@Test
	public void insertTest() {
		OneToOneDTO.FindAndCreate dto = new OneToOneDTO.FindAndCreate();
		dto.setTo_userno(2L);
		dto.setFrom_userno(3L);
		dao.create(dto);
		System.out.println(dto.toString());
	}

	@Test
	public void selectTest() {
		OneToOneDTO.FindAndCreate dto = new OneToOneDTO.FindAndCreate();
		dto.setTo_userno(1L);
		dto.setFrom_userno(2L);
	}

	@Test
	public void selectAll() {
		List<OneToOneVO.findAllVO> list = dao.selectAll(46L);

		for (OneToOneVO.findAllVO e : list) {
			System.out.println(e.toString());
		}
	}

	@Test
	public void findByPkTest() {
		OneToOneVO vo = dao.findByPk(24L);
		System.out.println(vo.toString());

	}
}
