package com.alore.growth.io.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alore.growth.io.model.Hotel;
import com.alore.growth.io.model.User;
import com.alore.growth.io.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	// for create user
	@PostMapping("/create")  
	public @ResponseBody User createUser(@RequestBody User user) {
		
		return userRepository.save(user);
	  }
	
	// for update user
	@PutMapping("/update")  
	public @ResponseBody User updateUser(@RequestBody User user) {
		User olduser = userRepository.findByEmail(user.getEmail());
		olduser.setName(user.getName());
		olduser.setPassword(user.getPassword());
		return userRepository.save(olduser);
	  }

}
