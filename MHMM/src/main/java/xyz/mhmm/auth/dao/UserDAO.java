package xyz.mhmm.auth.dao;

import xyz.mhmm.auth.AuthDTO;
import xyz.mhmm.auth.domain.UserVO;

public interface UserDAO {

	public void create(AuthDTO.Create user);

	public UserVO findByEmail(String email);
	
	public UserVO findById(String id);

	public void updateToName(UserVO user);

	public void updateToEmail(UserVO user);

	public void delete(Long no);

}
