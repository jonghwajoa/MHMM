package xyz.mhmm.chatRoom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.chatRoom.dao.OneToOneDAO;
import xyz.mhmm.chatRoom.dto.OneToOneDTO;

@Service
@Transactional
public class OneToOneService {

	private OneToOneDAO oneToOneDAO;

	public OneToOneService(OneToOneDAO oneToOneDAO) {
		this.oneToOneDAO = oneToOneDAO;
	}

	public void create(OneToOneDTO.create dto) {
		oneToOneDAO.createFromTo(dto);
		oneToOneDAO.createToFrom(dto);
	}

}
