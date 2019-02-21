package xyz.mhmm.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import xyz.mhmm.domain.UserVO;
import xyz.mhmm.persistence.UserDAO;
import xyz.mhmm.validation.UserValidation;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userdao;

	
	@Autowired
	UserValidation userValidation;
	
	@Override
	public void create(UserVO vo) {
		Errors errors = new BeanPropertyBindingResult(vo, "UserVO");
		userValidation.validate(vo, errors);

		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(e -> {
				Arrays.stream(e.getCodes()).forEach(System.out::println);
				System.out.println(e.getDefaultMessage());
			});
		} else {
			userdao.create(vo);
		}
	}
}
