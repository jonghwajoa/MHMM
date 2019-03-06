package xyz.mhmm.friend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.mhmm.friend.domain.FriendVO;

public interface FriendDAO {

	public void create(@Param("follower") Long follower, @Param("following") Long following);

	public List<FriendVO> findAll(Long follower);
}
