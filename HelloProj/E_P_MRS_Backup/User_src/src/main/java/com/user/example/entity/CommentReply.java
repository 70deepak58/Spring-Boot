package com.user.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//done by nitesh 
@Document(collection="commentsReply")
public class CommentReply {

	@Id
	private String id;
	private String userId;
	private String parentId;
	private String content;
	public CommentReply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentReply(String id, String userId, String parentId, String content) {
		super();
		this.id = id;
		this.userId = userId;
		this.parentId = parentId;
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
