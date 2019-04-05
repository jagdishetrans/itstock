package com.etrans.itstock.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etrans.itstock.model.User;
import com.etrans.itstock.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	UserRepository userRepository;

	public Optional<User> getUser(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName, password);
	}

	public boolean saveUser(User user) {
		if (user == null)
			return false;

		if (user.getUserName().trim().isEmpty() || user.getPassword().trim().isEmpty()
				|| user.getProfileName().trim().isEmpty())
			return false;

		userRepository.save(user);
		return true;
	}

//	public Optional<User> checkUser(Long id) {
//		return userRepository.findById(id);
//	}

//	public Optional<User> getUser(String userName, String password){
//		List<User> userList = userRepository.findAll();
//		User user = new User(userName, password);
//		userList = userList.stream().filter(u -> u.getUserName().contentEquals(user.getUserName()) && u.getPassword().contentEquals(user.getPassword())).collect(Collectors.toList());
//		if(userList.isEmpty()) {
//			return Optional.empty();
//		}else {
//			return Optional.of(userList.get(0));
//		}
//	}

}
