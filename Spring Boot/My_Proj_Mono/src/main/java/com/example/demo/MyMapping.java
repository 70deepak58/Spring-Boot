package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MyMapping {
	@Autowired
	MyUtils mu;
	//User Section
	@GetMapping("/getUsers")
	public List<User> GAU(){
		return mu.getAllUsers();
	}
	@PostMapping("/addUser")
	public User AU(@RequestBody User u) {
		return mu.addUser(u);
	}
	
	//Message Section
	@GetMapping("/getMessages")
	public List<Message> GAM(){
		return mu.getAllMessages();
	}
	
	@PostMapping("/addMessage")
	public Message AM(@RequestBody Message m) {
		return mu.addMessage(m);
	}
	
	//Comment Section 
	@GetMapping("/getComments")
	public List<Comment> GAC(){
		return mu.getAllComments();
	}
	
	@PostMapping("/addComment")
	public Comment AC(@RequestBody Comment m) {
		return mu.addComment(m);
	}
	
	//Like Section
	@GetMapping("/getLikes")
	public List<Like> GAL(){
		return mu.getAllLikes();
	}
	@PostMapping("/addLike")
	public Like AL(@RequestBody Like l) {
		return mu.addLike(l);
	}
	
	
	//Share Section
	@GetMapping("/shareMessage/{id}")
	public Message getShareMsg(@PathVariable int id) {
		return mu.getShareMsg(id);
	}

}
