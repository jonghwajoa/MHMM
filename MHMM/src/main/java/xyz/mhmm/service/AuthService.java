package xyz.mhmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.commons.UserDuplicatedException;
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

	public AuthDTO.Create create(AuthDTO.Create user) throws Exception {
		String id = user.getId();
		String email = user.getEmail();
		if (logindao.findExistById(id)) {
			throw new UserDuplicatedException("이미 사용중인 아이디 입니다. 다른 아이디를 사용해주세요.");
		}

		if (userdao.findExistByEmail(email)) {
			throw new UserDuplicatedException("이미 사용중인 이메일 입니다. 다른 이메이을 사용해주세요.");
		}

		userdao.create(user);
		logindao.create(user);
		return user;
	}

	public boolean findExistByEmail(String email) {
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
