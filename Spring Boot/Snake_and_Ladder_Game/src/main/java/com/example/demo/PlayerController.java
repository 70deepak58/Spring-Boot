package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class PlayerController {
	@Autowired
	private PlayerAdapter pa;
	
	@PostMapping("/register")
	public Player registerUser(@RequestBody Player p) {
		int pid = p.getId();
		List<Player> ap = pa.findAll();
		for (int i = 0; i < ap.size(); i++) {
			if (ap.get(i).getId() == pid) {
				p.setMessage("FAIL");
				return p;
			} 
		}
		    p.setMessage("Pass");
			pa.save(p);
		return p;
	}
	@PostMapping("/play")
	public List<Player> playUser(@RequestBody Player p){
		List<Player> lp=new ArrayList<Player>();
		lp.add(p);
		pa.save(lp.get(0));
		int p2t=1;
		int pid=p.getTag_id();
		Player q=pa.findById(pid).orElse(null);
		q.setTurn(p2t);
		lp.add(q);
		pa.save(lp.get(1));
		return lp;
	}
	@PostMapping("/login")
	public Player loginUser(@RequestBody Player p) {
		List<Player> ap=pa.findAll();
		int id=p.getId();
		String pass=p.getPassword();
		for(int i=0;i<ap.size();i++) {
			if((ap.get(i).getId()==id) && ap.get(i).getPassword().equals(pass)) {
				p.setId(ap.get(i).getId());
				p.setTag_id(ap.get(i).getTag_id());
				p.setScore(ap.get(i).getScore());
				p.setTurn(ap.get(i).getTurn());
				p.setMessage(ap.get(i).getMessage());
				return p;
			}	
		}
		
		return p;
	}
	@PostMapping("/reset")
	public Player resetUser(@RequestBody Player p) {
		String pass=p.getPassword();
		List<Player> ap=pa.findAll();
		int id=p.getId();
		Player p1=new Player();

		for(int i=0;i<ap.size();i++) {
			if((ap.get(i).getId()==id) && ap.get(i).getPassword().equals(pass)) {
				//Player 1 jugar
				p1.setId(p.getId());
				p1.setName(p.getName());
				p1.setPassword(p.getPassword());
				p1.setTag_id(p.getTag_id());
				p1.setScore(p.getScore());
				p1.setTurn(p.getTurn());
				p1.setMessage(p.getMessage());
				pa.save(p1);
				
				//Player 2 jugar
				Player p2=pa.findById(p.getTag_id()).orElse(null);
				if(p2==null) {
					p2=new Player();
					p2.setId(p.getTag_id());
					p2.setName(p.getName());
					p2.setPassword(p.getPassword());
					p2.setTag_id(p.getId());
					p2.setScore(p.getScore());
					p2.setTurn(p.getTurn()==0?1:0);
					p2.setMessage("dummy j");
					pa.save(p2);
					return p;
				}
				p2=new Player();
				p2.setId(p.getTag_id());
				p2.setName(p.getName());
				p2.setPassword(p.getPassword());
				p2.setTag_id(p.getId());
				p2.setScore(p.getScore());
				p2.setTurn(p2.getTurn());
				p2.setMessage(p2.getMessage());
				pa.save(p2);
				return p;
			}	
		}
		return p;
	}
	@PostMapping("/opuser")
	public Player getOpUser(@RequestBody Player p) {
		return pa.findById(p.getTag_id()).orElse(null);
	}
	@PostMapping("/user")
	public Player getUser(@RequestBody Player p) {
		return pa.findById(p.getId()).orElse(null);
	}

}
