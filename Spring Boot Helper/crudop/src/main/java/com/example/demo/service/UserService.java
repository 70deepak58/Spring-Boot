package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repos.UserRepository;

@Service
public class UserService {
    @Autowired
	private UserRepository userRepository;
	 public User createUser(User user) {
		 return userRepository.save(user);
	 }
	public List<User> createUsers(List<User>users){
		return (List<User>) userRepository.saveAll(users);
	}
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	public List<User> getUsers(){
		return (List<User>) userRepository.findAll();
		
	}
	public User updateUser(User user) {
		User old=new User();
		Optional<User> olduser=userRepository.findById(user.getId());
		if(olduser.isPresent()) {
			old=olduser.get();
			old.setName(user.getName());
			old.setAddress(user.getAddress());
			userRepository.save(old);
			
		}else {
			return new User();
		}
		return old;
	}
	public String deleteUserById(int id) {
		userRepository.deleteById(id);
		return "User got deleted";
	}
}
