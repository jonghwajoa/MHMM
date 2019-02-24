package xyz.mhmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Boolean create(UserDTO user) {
		String userId = user.getId();
		
		if(logindao.findById(userId)) {
			
			return false;
		};
		
		userdao.create(user);
		
		return true;
	}
}
