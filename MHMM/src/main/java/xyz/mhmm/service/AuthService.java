package xyz.mhmm.service;

import org.springframework.stereotype.Service;

import xyz.mhmm.domain.LoginDTO;
import xyz.mhmm.domain.UserDTO;

@Service
public interface AuthService {
	public boolean findExistByEmail(String email);
	public UserDTO create(UserDTO user) throws Exception;
	public void updateForUser(UserDTO user);
	public void updateForPw(LoginDTO login);

}
