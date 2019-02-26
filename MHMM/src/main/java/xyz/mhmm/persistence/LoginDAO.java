package xyz.mhmm.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.mhmm.domain.LoginVO;
import xyz.mhmm.domain.UserVO;
import xyz.mhmm.dto.AuthDTO;

@Repository
public class LoginDAO {

	@Autowired
	SqlSession sqlSession;

	private static final String namespace = "xyz.mhmm.mappers.loginMapper";

	public void create(AuthDTO.Create user) {
		sqlSession.insert(namespace + ".create", user);
	}

	public LoginVO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean findExistById(String id) {
		String isNull = sqlSession.selectOne(namespace + ".selectExistId", id);
		return isNull != null ? true : false;
	}

	public void updateToPw(LoginVO user) {
		sqlSession.update(namespace + ".updateToPw", user);
	}
}
