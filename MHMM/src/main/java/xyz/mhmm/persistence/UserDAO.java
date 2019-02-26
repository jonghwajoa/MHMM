package xyz.mhmm.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.mhmm.domain.UserVO;
import xyz.mhmm.dto.AuthDTO;

@Repository
public class UserDAO {

	@Autowired
	SqlSession sqlSession;

	private static final String namespace = "xyz.mhmm.mappers.userMapper";

	public void create(AuthDTO.Create user) {
		sqlSession.insert(namespace + ".create", user);
	}

	public boolean findExistByEmail(String email) {
		String isNull = sqlSession.selectOne(namespace + ".findExistByEmail", email);
		return isNull != null ? true : false;
	}

	public void updateToName(UserVO user) {
		sqlSession.update(namespace + ".updateToName", user);
	}

	public void updateToEmail(UserVO user) {
		sqlSession.update(namespace + ".updateToEmail", user);
	}

	public void delete(Long no) {
		sqlSession.delete(namespace + ".delete", no);
	}

}
