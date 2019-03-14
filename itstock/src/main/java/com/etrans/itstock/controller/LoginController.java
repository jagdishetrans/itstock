package com.etrans.itstock.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etrans.itstock.model.User;
import com.etrans.itstock.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	
	
//	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
//	public ResponseEntity<Object> requestUser(@PathVariable("id") Long id, HttpSession session) {		
//		Optional<User> user = loginService.checkUser(id);
//		if(user.isPresent())
//			return ResponseEntity.ok().body(user.get());
//		return ResponseEntity.notFound().build();
//	} 
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> requestLogin(@RequestParam("username") String username, @RequestParam("password") String pass, HttpSession session){
		Optional<User> user = loginService.getUser(username, pass);
		session.setAttribute("sessionId", user.get().getId());
		if(user.isPresent())
			return ResponseEntity.ok().body(user.get());
		return ResponseEntity.notFound().build();
	}
	
}
