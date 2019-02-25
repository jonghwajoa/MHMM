package xyz.mhmm.persistence;

import xyz.mhmm.domain.UserDTO;

public interface UserDAO {
	public void create(UserDTO user);
	public boolean findExistByEmail(String email);
	public void updateToName(UserDTO user);
	public void updateToEmail(UserDTO user);
	public void delete(Long no);
}
