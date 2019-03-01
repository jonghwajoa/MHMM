package xyz.mhmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.commons.EmailDuplicatedException;
import xyz.mhmm.commons.IdDuplicatedException;
import xyz.mhmm.commons.UserNotExistException;
import xyz.mhmm.domain.LoginVO;
import xyz.mhmm.domain.UserVO;
import xyz.mhmm.dto.AuthDTO;
import xyz.mhmm.persistence.LoginDAO;
import xyz.mhmm.persistence.UserDAO;

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

		if (loginDAO.findExistById(id)) {
			throw new IdDuplicatedException();
		}

		if (userdao.findExistByEmail(email)) {
			throw new EmailDuplicatedException();
		}

		userdao.create(user);
		loginDAO.create(user);
		return user;
	}

	public boolean duplicateCheckByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public void updateForUser(UserVO user) {
		// TODO Auto-generated method stub

	}

	public void updateForPw(LoginVO login) {
		// TODO Auto-generated method stub

	}

	public AuthDTO.Login Login(AuthDTO.Login dto) {

		LoginVO findUser = loginDAO.findById(dto.getId());

		if (findUser == null) {
			throw new UserNotExistException();
		}

		String bcryptPass = dto.getPw();

		if (!bcryptPass.equals(findUser.getPw())) {
			throw new UserNotExistException();
		}

		return dto;

	}
}
