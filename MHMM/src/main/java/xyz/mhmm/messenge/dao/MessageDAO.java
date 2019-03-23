package xyz.mhmm.messenge.dao;

import java.util.List;

import xyz.mhmm.messenge.MessageDTO;
import xyz.mhmm.messenge.domain.MessageVO;

public interface MessageDAO {
	public void create(MessageDTO dto);

	public List<MessageVO> findAll(Long chatroomNo);
}
