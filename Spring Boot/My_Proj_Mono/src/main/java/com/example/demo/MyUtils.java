package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUtils {
	@Autowired
	public UserAdapter ua;
	@Autowired
	public MessageAdapter ma;
	@Autowired
	public CommentAdapter ca;
	@Autowired
	public LikeAdapter la;
	
	//User section 
	public List<User> getAllUsers(){
		List<User> usr=new ArrayList<User>();
		usr=ua.findAll();
		return usr;
	}
	public User addUser(User u) {
		ua.save(u);
		return u;
	}
	
	
	
	
	//Message section 
	public List<Message> getAllMessages(){
		List<Message> msgs=new ArrayList<Message>();
		msgs=ma.findAll();
		return msgs;
	}
	public Message addMessage(Message m) {
		ma.save(m);
		return m;
	}
	
	
	//Comment Section 
	public List<Comment> getAllComments(){
		List<Comment> cmts=new ArrayList<Comment>();
		cmts=ca.findAll();
		return cmts;
	}
	public Comment addComment(Comment c) {
		Message msg=ma.findById(c.getMsg_id()).orElse(null);
		if(msg==null) return c;
		msg.setCmt_cnt(msg.getCmt_cnt()+1);
		Map<Integer, String> my_map=new HashMap<Integer, String>();
		Map<Integer, String> ge_map=msg.getCmt_map();
		for (Map.Entry<Integer,String> mapElement : ge_map.entrySet()) {
            int key = mapElement.getKey();
            String value =mapElement.getValue();
            my_map.put(key, value);
        }
		my_map.put(c.getId(), c.getMsg());
		msg.setCmt_map(my_map);
		ma.save(msg);
		return c;
	}
	
	
	//Like Section
	public List<Like> getAllLikes(){
		List<Like> lks=new ArrayList<Like>();
		lks=la.findAll();
		return lks;
	}
	public Like addLike(Like l) {
		Message msg=ma.findById(l.getMsg_id()).orElse(null);
		if(msg==null) return l;
		msg.setLike_cnt(msg.getLike_cnt()+1);
		
		Set<Integer> my_set=new HashSet<Integer>();
		Set<Integer> ge_set=msg.getLike_set();
		Iterator<Integer> value = ge_set.iterator();
        while (value.hasNext()) {
        	Integer v=(Integer) value.next();
        	my_set.add(v);
        }
        my_set.add(l.getId());
        msg.setLike_set(my_set);
        ma.save(msg);
		la.save(l);
		return l;
	}
	
	//Share Section
	public Message getShareMsg(int id) {
		Message msg=ma.findById(id).orElse(null);
		return msg;
	}

}
