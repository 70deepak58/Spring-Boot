package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class PlayerMapping {
	@Autowired
	private PlayerUtils pu;
	@PostMapping("/create")
	public Player CU(@RequestBody Player p) {
		
		return pu.createUser(p);
	}
	
	
	@PostMapping("/gs")
	public Player GU(@RequestBody Player p){
		return pu.getUsr(p);
	}
	
	
	@PostMapping("/lg")
	public Player LG(@RequestBody Player p){
		return pu.getLgn(p);
	}
	
	@PostMapping("/play")
	public List<Player> PU(@RequestBody Player p){
		return pu.playUsr(p);
	}
	
	

}
