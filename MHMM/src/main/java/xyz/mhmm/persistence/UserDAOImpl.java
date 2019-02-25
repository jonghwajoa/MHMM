package xyz.mhmm.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.mhmm.domain.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SqlSession sqlSession;

	private static final String namespace = "xyz.mhmm.mappers.userMapper";

	@Override
	public void create(UserDTO user) {
		sqlSession.insert(namespace + ".create", user);
	}

	@Override
	public boolean findExistByEmail(String email) {
		String isNull = sqlSession.selectOne(namespace + ".findExistByEmail", email);
		return isNull != null ? true : false;
	}

	@Override
	public void updateToName(UserDTO user) {
		sqlSession.update(namespace + ".updateToName", user);
	}

	@Override
	public void updateToEmail(UserDTO user) {
		sqlSession.update(namespace + ".updateToEmail", user);
	}

	@Override
	public void delete(Long no) {
		sqlSession.delete(namespace + ".delete", no);
	}

}
