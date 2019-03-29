package xyz.mhmm.mypage;

import java.io.File;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import xyz.mhmm.auth.dao.LoginDAO;
import xyz.mhmm.auth.domain.LoginVO;
import xyz.mhmm.mypage.exception.PwDidNotMatchException;

@Service
@Transactional
public class MyPageService {

	private LoginDAO loginDAO;
	private PasswordEncoder passwordEncoder;

	public MyPageService(LoginDAO loginDAO, PasswordEncoder passwordEncoder) {
		this.loginDAO = loginDAO;
		this.passwordEncoder = passwordEncoder;
	}

	public void passwordChange(MyPageDTO.PasswordChange dto) {

		String changePw = dto.getChangePw();
		dto.setChangePw(passwordEncoder.encode(changePw));
		LoginVO loginVO = loginDAO.findByPk(dto.getUserNo());

		if (passwordEncoder.matches(dto.getCurPW(), loginVO.getPw())) {
			throw new PwDidNotMatchException();
		}

		loginDAO.updatePw(dto);
	}

	public void changeProfilePhoto(MultipartFile photo) {
		UUID saveFileName = UUID.randomUUID();
		
		File saveFile = new File("path");
		
	}
}
