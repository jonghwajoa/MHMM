package xyz.mhmm.auth.dao;

import org.apache.ibatis.annotations.Param;

import xyz.mhmm.auth.AuthDTO;
import xyz.mhmm.auth.domain.UserVO;

public interface UserDAO {

	public void create(AuthDTO.Create user);

	public UserVO findByEmail(String email);

	public UserVO findById(String id);

	public UserVO findByPk(Long no);

	public void updateToName(UserVO user);

	public void updateToEmail(UserVO user);

	public void updateToPhoto(@Param("path") String photoPath, @Param("no") Long no);

	public void delete(Long no);

}
