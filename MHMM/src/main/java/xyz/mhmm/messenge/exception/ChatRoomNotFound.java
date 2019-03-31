package xyz.mhmm.messenge.exception;

import xyz.mhmm.exception.BusinessException;
import xyz.mhmm.utils.ErrorCode;

@SuppressWarnings("serial")
public class ChatRoomNotFound extends BusinessException {
	public ChatRoomNotFound() {
		super(ErrorCode.CHATROOM_NOT_FOUND);
	}
}
