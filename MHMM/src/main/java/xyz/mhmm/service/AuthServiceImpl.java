package xyz.mhmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.mhmm.domain.LoginDTO;
import xyz.mhmm.domain.UserDTO;
import xyz.mhmm.persistence.LoginDAO;
import xyz.mhmm.persistence.UserDAO;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserDAO userdao;

	@Autowired
	LoginDAO logindao;

	@Override
	public boolean create(UserDTO user) {
		String userId = user.getId();
		if (logindao.findExistById(userId)) {
			return false;
		}
		userdao.create(user);
		logindao.create(user);
		return true;
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
