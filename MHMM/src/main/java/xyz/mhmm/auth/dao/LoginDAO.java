package xyz.mhmm.auth.dao;

import xyz.mhmm.auth.AuthDTO;
import xyz.mhmm.auth.domain.LoginVO;
import xyz.mhmm.mypage.MyPageDTO;

public interface LoginDAO {

	public void create(AuthDTO.Create user);

	public LoginVO findById(String id);
	public LoginVO findByPk(Long no);

	public boolean selectExistId(String id);

	public void updatePw(MyPageDTO.PasswordChange user);
	

}
