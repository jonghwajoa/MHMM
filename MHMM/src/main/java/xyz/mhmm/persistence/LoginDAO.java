package xyz.mhmm.persistence;

import xyz.mhmm.domain.LoginDTO;
import xyz.mhmm.domain.UserDTO;

public interface LoginDAO {
	
	public Long create(UserDTO user);
	public boolean findExistById(String id);
	public LoginDTO findById(String id);
	public void updateToPw(LoginDTO user);
}
