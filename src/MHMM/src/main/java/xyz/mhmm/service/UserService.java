package xyz.mhmm.service;

import org.springframework.stereotype.Service;

import xyz.mhmm.domain.UserVO;

@Service
public interface UserService {

	public void create(UserVO vo);

}
