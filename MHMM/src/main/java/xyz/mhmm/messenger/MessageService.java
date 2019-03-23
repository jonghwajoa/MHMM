package xyz.mhmm.messenger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.messenger.dao.MessageDAO;

@Service
@Transactional
public class MessageService {

	MessageDAO messageDAO;

	public MessageService(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	public void create(MessageDTO dto) {
		messageDAO.create(dto);
	}
}
