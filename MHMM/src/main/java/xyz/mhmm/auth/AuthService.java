package xyz.mhmm.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
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

	private UserDAO userdao;
	private LoginDAO loginDAO;
	private PasswordEncoder passwordEncoder;

	public AuthService(UserDAO userdao, LoginDAO loginDAO, PasswordEncoder passwordEncoder) {
		this.userdao = userdao;
		this.loginDAO = loginDAO;
		this.passwordEncoder = passwordEncoder;
	}

	public AuthDTO.Create create(AuthDTO.Create user) {

		String id = user.getId();
		String email = user.getEmail();

		if (userdao.findById(id) != null) {
			throw new IdDuplicatedException();
		}

		if (userdao.findByEmail(email) != null) {
			throw new EmailDuplicatedException();
		}

		user.setPw(passwordEncoder.encode(user.getPw()));

		userdao.create(user);
		loginDAO.create(user);
		return user;
	}

	public LoginVO Login(AuthDTO.Login dto) {

		LoginVO findUserVO = loginDAO.findById(dto.getId());

		if (findUserVO == null) {
			throw new InvalidLoginInput();
		}

		if (!dto.getPw().equals(findUserVO.getPw()) && !passwordEncoder.matches(dto.getPw(), findUserVO.getPw())) {
			throw new InvalidLoginInput();
		}

		return findUserVO;

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
