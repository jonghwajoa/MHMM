package xyz.mhmm.mypage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import xyz.mhmm.auth.dao.LoginDAO;
import xyz.mhmm.auth.dao.UserDAO;
import xyz.mhmm.auth.domain.LoginVO;
import xyz.mhmm.auth.domain.UserVO;
import xyz.mhmm.mypage.exception.PwDidNotMatchException;

@Service
@Transactional
public class MyPageService {

	private LoginDAO loginDAO;
	private UserDAO userDAO;
	private PasswordEncoder passwordEncoder;

	public MyPageService(LoginDAO loginDAO, PasswordEncoder passwordEncoder, UserDAO userDAO) {
		this.loginDAO = loginDAO;
		this.passwordEncoder = passwordEncoder;
		this.userDAO = userDAO;
	}

	public String getMyPage(Long no) {
		UserVO vo = userDAO.findByPk(no);
		return vo.getPhoto_path();
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

	public String changeProfilePhoto(MultipartFile photo, String path, Long userNo) {
		UUID saveFileName = null;
		File file = null;
		String uploadPath = null, fileFullName = null;
		String originFileName = photo.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());

		try {
			do {
				saveFileName = UUID.randomUUID();
				fileFullName = saveFileName + ext;
				uploadPath = FileUpload.userPhotoPath + saveFileName + ext;
				file = new File(uploadPath);
			} while (file.exists());

			byte[] bytes = photo.getBytes();
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}

		userDAO.updateToPhoto(saveFileName + ext, userNo);
		return fileFullName;
	}
}
