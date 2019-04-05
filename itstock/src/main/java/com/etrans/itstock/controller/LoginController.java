package com.etrans.itstock.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.etrans.itstock.model.User;
import com.etrans.itstock.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createUser(@RequestBody User user) {
		if (!loginService.saveUser(user)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "username/password/profile name is mandatory");
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> requestLogin(@RequestBody User user) {
		Optional<User> oUser = loginService.getUser(user.getUserName(), user.getPassword());
		if (oUser.isPresent())
			return ResponseEntity.ok().body(oUser.get());
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "username or password is wrong");
	}

}
