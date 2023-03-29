package com.example.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
@RequestMapping("/message")
public class MessageController {
@Autowired
private MessageAdapter ma;
@GetMapping("/getallmessage")
public List<Message> getMessages(){
	List<Message> msgs=ma.findAll();
	return msgs;
}
@PostMapping("/messageid/{id}")
public Message getOneMsg(@PathVariable int id) {
	Message msg=ma.findById(id).orElse(null);
	return msg;
}
@PostMapping("/addmessage")
public Message addMessage(@RequestBody Message msg) {
	ma.save(msg);
	return msg;
}

@PostMapping("/deletemessage")
public Message deleteMessage(@RequestBody Message msg) {
	Message dmsg=ma.findById(msg.getId()).orElse(null);
	if(dmsg==null) return msg;
	ma.deleteById(msg.getId());
	return msg;
}

@PostMapping("/deletecomment")
public Message deleteComment(@RequestBody Message msg) {
	Message dmsg=ma.findById(msg.getId()).orElse(null);
	if(dmsg==null) return msg;
	int x=msg.getComment_count();
	Map<Integer, String> my_map=new HashMap<Integer, String>();
	Map<Integer, String> ge_map=dmsg.getComment_map();
	for (Map.Entry<Integer,String> mapElement : ge_map.entrySet()) {
        int key = mapElement.getKey();
        String value =mapElement.getValue();
        if(key!=x) {
        	my_map.put(key, value);
        }
    }
	dmsg.setComment_map(my_map);
    ma.save(dmsg);
	return msg;
}


@PostMapping("/editcomment")
public Message editComment(@RequestBody Message msg) {
	Message dmsg=ma.findById(msg.getId()).orElse(null);
	if(dmsg==null) return msg;
	int x=msg.getComment_count();
	Map<Integer, String> my_map=new HashMap<Integer, String>();
	Map<Integer, String> ge_map=dmsg.getComment_map();
	for (Map.Entry<Integer,String> mapElement : ge_map.entrySet()) {
        int key = mapElement.getKey();
        String value =mapElement.getValue();
        if(key!=x) {
        	my_map.put(key, value);
        }
        else {
        	my_map.put(key, msg.getMessage());
        }
    }
	dmsg.setComment_map(my_map);
    ma.save(dmsg);
	return msg;
}

@PostMapping("/editmessage")
public Message editMessage(@RequestBody Message msg) {
	Message newmsg=ma.findById(msg.getId()).orElse(null);
	if(newmsg==null) {
		return msg;
	}
	newmsg.setMessage(msg.getMessage());
	ma.save(newmsg);
	return msg;
}

@PostMapping("/commentonpost")
public String addingCommentOnMessage(@RequestBody String lst){
	try {
		Message dummy_cmt_msg =new ObjectMapper().readValue(lst, Message.class);
		Message msg=ma.findById(dummy_cmt_msg.getId()).orElse(null);
		if(msg==null) return lst;
		msg.setComment_count(msg.getComment_count()+1);
		Map<Integer, String> my_map=new HashMap<Integer, String>();
		Map<Integer, String> ge_map=msg.getComment_map();
		for (Map.Entry<Integer,String> mapElement : ge_map.entrySet()) {
            int key = mapElement.getKey();
            String value =mapElement.getValue();
            my_map.put(key, value);
        }
		my_map.put(dummy_cmt_msg.getComment_count(), dummy_cmt_msg.getMessage());
		msg.setComment_map(my_map);
		ma.save(msg);
		return lst;
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	 return lst;
}

@PostMapping("/likeamessage")
public String addLikeAMessage(@RequestBody String lst) {
	try {
		Message dummy_cmt_msg =new ObjectMapper().readValue(lst, Message.class);
		Message msg=ma.findById(dummy_cmt_msg.getId()).orElse(null);
		if(msg==null) return lst;
		msg.setLike_count(msg.getLike_count()+1);
		Set<Integer> my_set=new HashSet<Integer>();
		Set<Integer> ge_set=msg.getLike_set();
		Iterator<Integer> value = ge_set.iterator();
		while (value.hasNext()) {
        	Integer v=(Integer) value.next();
        	my_set.add(v);
        }
        my_set.add(dummy_cmt_msg.getLike_count());
        msg.setLike_set(my_set);
        ma.save(msg);
        return lst;		
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	 return lst;
	
}


}
