package xyz.mhmm.friend;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.auth.dao.UserDAO;
import xyz.mhmm.auth.domain.UserVO;
import xyz.mhmm.auth.exception.InvalidLoginInput;
import xyz.mhmm.friend.dao.FriendDAO;
import xyz.mhmm.friend.domain.FriendVO;
import xyz.mhmm.friend.exception.AlreadyFriendException;
import xyz.mhmm.friend.exception.OwnSelfAddFriendException;
import xyz.mhmm.friend.exception.SearchNotFound;

@Service
@Transactional
public class FriendService {

	private UserDAO userDAO;
	private FriendDAO friendDAO;

	public FriendService(UserDAO userDAO, FriendDAO friendDAO) {
		this.userDAO = userDAO;
		this.friendDAO = friendDAO;
	}

	public List<FriendVO.list> findAll(Long myNo) {
		List<FriendVO.list> list = friendDAO.findAll(myNo);

		System.out.println(list.toString());

		return list;
	}

	public UserVO create(Long myNo, FriendDTO.Add dto) {

		String friendId = dto.getId();
		UserVO userVO = userDAO.findById(friendId);

		if (userVO == null) {
			throw new InvalidLoginInput();
		}

		if (myNo == userVO.getNo()) {
			throw new OwnSelfAddFriendException();
		}

		try {
			friendDAO.create(myNo, userVO.getNo());
		} catch (DuplicateKeyException e) {
			throw new AlreadyFriendException();
		}

		return userVO;
	}

	public void delete(Long myNo, FriendDTO.Delete dto) {
		Long targetNo = dto.getNo();
		friendDAO.delete(myNo, targetNo);
	}

	public UserVO search(Long myNo, FriendDTO.Search dto) {

		UserVO userVO = userDAO.findById(dto.getId());

		if (userVO == null) {
			throw new SearchNotFound();
		}

		if (myNo == userVO.getNo()) {
			throw new OwnSelfAddFriendException();
		}

		return userVO;
	}
}
