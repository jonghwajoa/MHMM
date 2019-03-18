package xyz.mhmm.chatRoom.dao;

import xyz.mhmm.chatRoom.domain.OneToOneVO;
import xyz.mhmm.chatRoom.dto.OneToOneDTO;

public interface OneToOneDAO {

	public void createFromTo(OneToOneDTO.create dto);

	public void createToFrom(OneToOneDTO.create dto);

	public OneToOneVO selectFromTo(OneToOneDTO.create dto);

	public OneToOneVO selectToFrom(OneToOneDTO.create dto);
}
