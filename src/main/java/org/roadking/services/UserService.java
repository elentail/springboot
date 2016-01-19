package org.roadking.services;

import org.roadking.model.User;
import org.roadking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository repository){
		this.userRepository=repository;
	}
	public User getUserByName(String name){
		return userRepository.findByName(name);
	}

}
