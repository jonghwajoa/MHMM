package xyz.mhmm.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import xyz.mhmm.domain.UserDTO;
import xyz.mhmm.service.AuthService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({ @ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" }),
		@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) })
@WebAppConfiguration
public class AuthTests {

	@Autowired
	AuthService authService;

	@Test
	public void SingupTest() {
		UserDTO user = createUserDto();
		authService.create(user);
	}

	public UserDTO createUserDto() {
		UserDTO user = new UserDTO();
		user.setEmail("21322mhmm@mhmm.xyz");
		user.setName("종화");
		user.setId("mhmmid");
		user.setPw("userpw");
		return user;
	}

}
