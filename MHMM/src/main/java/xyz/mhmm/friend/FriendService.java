package xyz.mhmm.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.auth.dao.LoginDAO;
import xyz.mhmm.auth.domain.LoginVO;
import xyz.mhmm.auth.exception.UserNotExistException;
import xyz.mhmm.friend.dao.FriendDAO;
import xyz.mhmm.friend.domain.FriendVO;
import xyz.mhmm.friend.exception.AlreadyFriendException;
import xyz.mhmm.friend.exception.OwnSelfAddFriendException;

@Service
@Transactional
public class FriendService {

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private FriendDAO friendDAO;

	public void create(Long myNo, FriendDTO.Add dto) {

		String friendId = dto.getId();
		LoginVO loginVO = loginDAO.findById(friendId);
		if (loginVO == null) {
			throw new UserNotExistException();
		}
		
		
		if(myNo ==loginVO.getNo()) {
			throw new OwnSelfAddFriendException();	
		}

		try {
			friendDAO.create(myNo, loginVO.getNo());
		} catch (DuplicateKeyException e) {
			throw new AlreadyFriendException();
		}
	}
}
