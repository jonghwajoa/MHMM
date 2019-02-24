package xyz.mhmm.service;

import org.springframework.stereotype.Service;

import xyz.mhmm.domain.UserDTO;

@Service
public interface AuthService {
	
	

	public Boolean create(UserDTO vo);

}
