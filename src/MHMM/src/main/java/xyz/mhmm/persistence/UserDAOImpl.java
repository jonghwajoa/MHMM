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
	public void create(UserDTO dto) {
		int user = sqlSession.insert(namespace + ".create", dto);
		System.out.println(user);
		System.out.println(sqlSession.insert(namespace + ".create", dto));
		System.out.println(sqlSession.insert(namespace + ".create", dto));
	}

	@Override
	public boolean findExistByEmail(String email) {
		String isNull = sqlSession.selectOne(namespace + ".findExistByEmail", email);
		return isNull !=null ? true : false;
	}
	
}
