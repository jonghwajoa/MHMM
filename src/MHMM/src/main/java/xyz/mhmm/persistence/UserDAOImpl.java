package xyz.mhmm.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.mhmm.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SqlSession sqlSession;

	private static final String namespace = "xyz.mhmm.mappers.userMapper";

	@Override
	public void create(UserVO vo) {
		System.out.println("여기는온다.");
		sqlSession.insert(namespace + ".create", vo);
	}
}
