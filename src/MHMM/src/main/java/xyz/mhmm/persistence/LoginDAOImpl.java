package xyz.mhmm.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO{

	@Autowired
	SqlSession sqlSession;

	private static final String namespace = "xyz.mhmm.mappers.loginMapper";
	
	@Override
	public void create(LoginDAO dto) {
		
		
	}
	@Override
	public boolean findExistById(String userId) {
		String isNull = sqlSession.selectOne(namespace + ".selectExistId", userId);
		return isNull != null ? true : false;
	}

}
