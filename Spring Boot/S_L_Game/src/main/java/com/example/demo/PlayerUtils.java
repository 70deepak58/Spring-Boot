package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerUtils {
	@Autowired
	PlayerInterface pi;

	public Player createUser(Player p) {
		int id = p.getId();
		List<Player> ap = pi.findAll();
		//String stat;
		for (int i = 0; i < ap.size(); i++) {
			if (ap.get(i).getId() == id) {
				p.setMsg("Fail");
				//pi.save(p);
				return p;
			} 
		}
		    p.setMsg("Pass");
			pi.save(p);
			//stat = "Pass";
		
		return p;
	}
	
	
	
	
	public List<Player> playUsr(Player p){
		List<Player> lp=new ArrayList<Player>();
		lp.add(p);
		pi.save(lp.get(0));
		int p2t=1;
		int pid=p.getStatus();
		Player q=pi.findById(pid).orElse(null);
		q.setTurn(p2t);
		lp.add(q);
		pi.save(lp.get(1));
		return lp;
	}
	
	public Player getUsr(Player p) {
		Player q=pi.findById(p.getStatus()).orElse(null);
		return q;
	}
	
	public Player getLgn(Player p) {
		List<Player> ap=pi.findAll();
		String usr=p.getName();
		String pass=p.getPass();
		//Player ans;
		for(int i=0;i<ap.size();i++) {
			if(ap.get(i).getName().equals(usr) && ap.get(i).getPass().equals(pass)) {
				p.setId(ap.get(i).getId());
				p.setStatus(ap.get(i).getStatus());
				p.setScore(ap.get(i).getScore());
				p.setTurn(ap.get(i).getTurn());
				p.setMsg(ap.get(i).getMsg());
				return p;
			}
			
		}
		
		return p;
	}


}
