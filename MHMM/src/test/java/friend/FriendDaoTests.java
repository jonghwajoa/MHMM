package friend;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.friend.dao.FriendDAO;
import xyz.mhmm.friend.domain.FriendVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, DBConfig.class })
@WebAppConfiguration
@Transactional
public class FriendDaoTests {

	@Autowired
	FriendDAO friendDAO;

	@Test
	@Description("친구 추가")
	public void addTest() {
		friendDAO.create(12L, 16L);
	}

	@Test
	public void findAllTest() {
		List<FriendVO.list> list = friendDAO.findAll(4L);
		
		for (FriendVO.list e : list) {
			System.out.println(e);
		}
	}
	
	@Test
	@Description("친구 삭제")
	public void deleteTest() {
		friendDAO.delete(12L, 16L);
		
	}
}
