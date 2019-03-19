package xyz.mhmm.chatRoom.dao;

import java.util.List;

import xyz.mhmm.chatRoom.domain.OneToOneVO;
import xyz.mhmm.chatRoom.dto.OneToOneDTO;

public interface OneToOneDAO {

	public void create(OneToOneDTO.FindAndCreate dto);

	public OneToOneVO select(OneToOneDTO.FindAndCreate dto);

	public List<OneToOneVO> selectAll(Long userNo);
}
