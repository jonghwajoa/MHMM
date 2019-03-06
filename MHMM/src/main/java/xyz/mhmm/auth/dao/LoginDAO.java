package xyz.mhmm.auth.dao;

import xyz.mhmm.auth.AuthDTO;
import xyz.mhmm.auth.domain.LoginVO;

public interface LoginDAO {

	public void create(AuthDTO.Create user);

	public LoginVO findById(String id);

	public boolean selectExistId(String id);

	public void updateToPw(LoginVO user);
}
