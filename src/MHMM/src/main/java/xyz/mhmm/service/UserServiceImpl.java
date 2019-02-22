package xyz.mhmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.mhmm.domain.UserVO;
import xyz.mhmm.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userdao;

	@Override
	public void create(UserVO vo) {

		userdao.create(vo);
	}
}
