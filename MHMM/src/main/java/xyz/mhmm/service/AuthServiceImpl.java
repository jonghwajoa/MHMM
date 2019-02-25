package xyz.mhmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.domain.LoginDTO;
import xyz.mhmm.domain.UserDTO;
import xyz.mhmm.errors.UserDuplicatedException;
import xyz.mhmm.persistence.LoginDAO;
import xyz.mhmm.persistence.UserDAO;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserDAO userdao;

	@Autowired
	LoginDAO logindao;

	@Override
	public UserDTO create(UserDTO user) throws Exception {
		String id = user.getId();
		String email = user.getEmail(); 
		if (logindao.findExistById(id)) {
			throw new UserDuplicatedException("존재하는 아이디 입니다. 다른 아이디를 입력해 주세요.");
		}
		
		if (userdao.findExistByEmail(email)) {
			throw new UserDuplicatedException("존재하는 이메일 입니다. 다른 이메를 입력해 주세요.");
		}
		
		userdao.create(user);
		logindao.create(user);
		return user;
	}

	@Override
	public boolean findExistByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateForUser(UserDTO user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateForPw(LoginDTO login) {
		// TODO Auto-generated method stub

	}
}
