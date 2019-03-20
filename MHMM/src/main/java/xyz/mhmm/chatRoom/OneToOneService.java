package xyz.mhmm.chatRoom;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.mhmm.chatRoom.dao.OneToOneDAO;
import xyz.mhmm.chatRoom.domain.OneToOneVO;
import xyz.mhmm.chatRoom.dto.OneToOneDTO;

@Service
@Transactional
public class OneToOneService {

	private OneToOneDAO oneToOneDAO;

	public OneToOneService(OneToOneDAO oneToOneDAO) {
		this.oneToOneDAO = oneToOneDAO;
	}

	public Long create(OneToOneDTO.FindAndCreate dto) {

		swap(dto);
		OneToOneVO vo = oneToOneDAO.select(dto);

		if (vo != null) {
			return vo.getNo();
		}

		oneToOneDAO.create(dto);
		return dto.getNo();
	}

	public void select(OneToOneDTO.FindAndCreate dto) {
	}

	public List<OneToOneVO.findAllVO> findAll(Long userNo) {
		return oneToOneDAO.selectAll(userNo);
	}

	public void delete(Long no) {

	}

	private void swap(OneToOneDTO.FindAndCreate dto) {
		Long toUserNo = dto.getTo_userno();
		Long fromUserNo = dto.getFrom_userno();

		if (toUserNo < fromUserNo) {
			dto.setFrom_userno(toUserNo);
			dto.setTo_userno(fromUserNo);
		}
	}

}
