package xyz.mhmm.persistence;

import xyz.mhmm.domain.UserDTO;

public interface LoginDAO {
	public void create(LoginDAO dto);
	
	public boolean findExistById(String userId);
}
