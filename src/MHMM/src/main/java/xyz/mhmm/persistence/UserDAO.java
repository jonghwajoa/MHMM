package xyz.mhmm.persistence;

import xyz.mhmm.domain.UserDTO;

public interface UserDAO {
	public void create(UserDTO dto);
	
	public boolean findExistByEmail(String email);
}
