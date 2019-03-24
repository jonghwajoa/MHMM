package mypage;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.config.DBConfig;
import xyz.mhmm.config.WebApplication;
import xyz.mhmm.config.WebConfig;
import xyz.mhmm.mypage.MyPageDTO;
import xyz.mhmm.mypage.MyPageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebApplication.class, WebConfig.class, DBConfig.class })
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyPageServiceTest {

	@Autowired
	MyPageService myPageService;

	@Test
	public void passwordChange() {
		MyPageDTO.PasswordChange dto = new MyPageDTO.PasswordChange();
		dto.setUserNo(45L);
		dto.setChangePw("user1");
		myPageService.passwordChange(dto);

	}
}
