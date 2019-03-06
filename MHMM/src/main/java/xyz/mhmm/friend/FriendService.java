package xyz.mhmm.friend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private FriendDAO friendDAO;

	public List<FriendVO.list> findAll(Long myNo) {
		return friendDAO.findAll(myNo);
	}

	public void create(Long myNo, FriendDTO.Add dto) {

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
	}

	public void delete(Long myNo, FriendDTO.Delete dto) {
		Long targetNo = dto.getNo();
		friendDAO.delete(myNo, targetNo);
	}

	public UserVO search(FriendDTO.Search dto) {

		UserVO userVO = userDAO.findById(dto.getId());

		if (userVO == null) {
			throw new SearchNotFound();
		}
		// TODO : 검색 허용안할시 처리 기능 추가할때 수정하기

		return userVO;
	}
}
