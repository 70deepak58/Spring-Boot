package com.user.example.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="comments")
public class Comment {

	@Id
	private String id;
	private String product_id;
	private String emp_id;
	private String content;
	private List<String> emoji = new ArrayList<String>();
	private List<String> reply = new ArrayList<String>();
	private Map<String, String> emojimap= new HashMap<String, String>();
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(String id, String product_id, String emp_id, String content,List<String> emoji,List<String> reply,Map<String, String> emojimap) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.emp_id = emp_id;
		this.content = content;
		this.emoji = emoji;
		this.reply= reply;
		this.emojimap = emojimap;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getEmoji() {
		return emoji;
	}
	public void setEmoji(List<String> emoji) {
		this.emoji = emoji;
	}
	public List<String> getReply() {
		return reply;
	}
	public void setReply(List<String> reply) {
		this.reply = reply;
	}
	public Map<String, String> getEmojimap() {
		return emojimap;
	}
	public void setEmojimap(Map<String, String> emojimap) {
		this.emojimap = emojimap;
	}

	
}
