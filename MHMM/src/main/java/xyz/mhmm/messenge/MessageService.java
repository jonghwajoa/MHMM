package xyz.mhmm.messenge;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.chatRoom.dao.OneToOneDAO;
import xyz.mhmm.chatRoom.domain.OneToOneVO;
import xyz.mhmm.messenge.dao.MessageDAO;
import xyz.mhmm.messenge.domain.MessageVO;
import xyz.mhmm.messenge.exception.ChatRoomNotFound;
import xyz.mhmm.messenge.exception.NotAccessException;

@Service
@Transactional
public class MessageService {

	private MessageDAO messageDAO;
	private OneToOneDAO oneToOneDAO;

	public MessageService(MessageDAO messageDAO, OneToOneDAO oneToOneDAO) {
		this.messageDAO = messageDAO;
		this.oneToOneDAO = oneToOneDAO;
	}

	public void create(MessageDTO dto) {
		messageDAO.create(dto);
	}

	public List<MessageVO> oneToOneChatFindAll(MessageDTO.noOnly dto) {
		Long userNo = dto.getUser_no();
		OneToOneVO oneToOneVO = oneToOneDAO.findByPk(dto.getChatroom_no());
		if (oneToOneVO == null) {
			throw new ChatRoomNotFound();
		}

		if (oneToOneVO.getFrom_userno() != userNo && oneToOneVO.getTo_userno() != userNo) {
			throw new NotAccessException();
		}

		return messageDAO.findAll(dto.getChatroom_no());
	}
}
