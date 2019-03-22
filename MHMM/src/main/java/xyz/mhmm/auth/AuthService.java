package xyz.mhmm.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.auth.dao.LoginDAO;
import xyz.mhmm.auth.dao.UserDAO;
import xyz.mhmm.auth.domain.LoginVO;
import xyz.mhmm.auth.domain.UserVO;
import xyz.mhmm.auth.exception.EmailDuplicatedException;
import xyz.mhmm.auth.exception.IdDuplicatedException;
import xyz.mhmm.auth.exception.InvalidLoginInput;

@Service
@Transactional
public class AuthService {

	@Autowired
	UserDAO userdao;

	@Autowired
	LoginDAO loginDAO;

	public AuthDTO.Create create(AuthDTO.Create user) {

		String id = user.getId();
		String email = user.getEmail();

		if (userdao.findById(id) != null) {
			throw new IdDuplicatedException();
		}

		if (userdao.findByEmail(email) != null) {
			throw new EmailDuplicatedException();
		}

		userdao.create(user);
		loginDAO.create(user);
		return user;
	}

	public LoginVO Login(AuthDTO.Login dto) {

		LoginVO findUser = loginDAO.findById(dto.getId());

		if (findUser == null) {
			throw new InvalidLoginInput();
		}
		
		// TODO : PASSWORD 암호화
		String bcryptPass = dto.getPw();

		if (!bcryptPass.equals(findUser.getPw())) {
			throw new InvalidLoginInput();
		}

		return findUser;

	}

	public LoginVO existUserById(String id) {
		return loginDAO.findById(id);
	}

	public void updateForUser(UserVO user) {
		// TODO Auto-generated method stub

	}

	public void updateForPw(LoginVO login) {
		// TODO Auto-generated method stub

	}

}
