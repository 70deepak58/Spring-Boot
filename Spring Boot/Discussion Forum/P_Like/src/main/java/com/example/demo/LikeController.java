package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/like")
public class LikeController {
	@Autowired
	private FeignAdapter fa;
	
@PostMapping("/likeacomment")
public String LAM(@RequestBody String lst) throws JSONException {
	try {
		
		    Like lk=new ObjectMapper().readValue(lst, Like.class);
		    JSONObject jobj=new JSONObject();

			jobj.put("id", lk.getMessage_id());
			jobj.put("message", "");
			jobj.put("comment_count", 0);
			jobj.put("like_count", lk.getId());
			jobj.put("user_id", 0);
			return fa.addLikeAMessage(jobj.toString());
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
