package xyz.mhmm.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.mhmm.domain.LoginDTO;
import xyz.mhmm.domain.UserDTO;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	SqlSession sqlSession;

	private static final String namespace = "xyz.mhmm.mappers.loginMapper";

	@Override
	public void create(UserDTO user) {
		sqlSession.insert(namespace + ".create", user);
	}

	@Override
	public LoginDTO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findExistById(String id) {
		String isNull = sqlSession.selectOne(namespace + ".selectExistId", id);
		return isNull != null ? true : false;
	}

	@Override
	public void updateToPw(LoginDTO user) {
		sqlSession.update(namespace + ".updateToPw", user);
	}
}
