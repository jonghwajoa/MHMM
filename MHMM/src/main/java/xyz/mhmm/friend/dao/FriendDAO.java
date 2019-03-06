package xyz.mhmm.friend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.mhmm.friend.FriendDTO;
import xyz.mhmm.friend.domain.FriendVO;

public interface FriendDAO {

	public void create(@Param("follower") Long follower, @Param("following") Long following);

	public List<FriendVO.list> findAll(Long follower);
	
	public void delete(@Param("myNo") Long myNo, @Param("targetNo") Long targetNo);
	
}
