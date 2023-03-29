package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/comment")
public class CommentMapping {
	@Autowired
	private FeignAdapter fa;
	@Autowired
	private CommentAdapter ca;
	@GetMapping("/getallcomment")
	public List<Comment> getMessages(){
		List<Comment> cmts=ca.findAll();
		return cmts;
	}
	@PostMapping("/commentid{id}")
	public Comment getOneMsg(@PathVariable int id) {
		Comment cmt=ca.findById(id).orElse(null);
		return cmt;
	}
	@PostMapping("/addcomment")
	public Comment addMessage(@RequestBody Comment cmt) {
		ca.save(cmt);
		return cmt;
	}
	@GetMapping("/getallmessage")
	public String ga() {
		return fa.getMessages();
	}
	@PostMapping("/commentonpost")
	public String ACOM(@RequestBody String lst) throws JSONException {
	try {
			Comment cmt=new ObjectMapper().readValue(lst, Comment.class);
			JSONObject jobj =new JSONObject();
			jobj.put("id", cmt.getMessage_id());
			jobj.put("message", cmt.getComment());
			jobj.put("comment_count", cmt.getId());
			jobj.put("like_count", 0);
			jobj.put("user_id", 0);
			 return fa.addingCommentOnMessage(jobj.toString());
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
