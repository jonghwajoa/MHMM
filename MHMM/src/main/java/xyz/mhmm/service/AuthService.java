package xyz.mhmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.commons.EmailDuplicatedException;
import xyz.mhmm.commons.IdDuplicatedException;
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
	LoginDAO logindao;

	public AuthDTO.Create create(AuthDTO.Create user) {
		String id = user.getId();
		String email = user.getEmail();
		if (logindao.findExistById(id)) {
			throw new IdDuplicatedException();
		}

		if (userdao.findExistByEmail(email)) {
			throw new EmailDuplicatedException();
		}

		userdao.create(user);
		logindao.create(user);
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
}
