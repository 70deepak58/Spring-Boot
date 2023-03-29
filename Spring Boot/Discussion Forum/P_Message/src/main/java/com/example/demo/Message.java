package com.example.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document("MESSAGE")
public class Message {
@Id
public int id;
public String message;
public int comment_count;
public int like_count;
public int user_id;
public Map<Integer,String> comment_map= new HashMap<Integer,String>();
public Set<Integer> like_set = new HashSet<Integer> ();
public int getId() {
	return id;
}
public Map<Integer, String> getComment_map() {
	return comment_map;
}
public void setComment_map(Map<Integer, String> comment_map) {
	this.comment_map = comment_map;
}
public Set<Integer> getLike_set() {
	return like_set;
}
public void setLike_set(Set<Integer> like_set) {
	this.like_set = like_set;
}
public void setId(int id) {
	this.id = id;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getComment_count() {
	return comment_count;
}
public void setComment_count(int comment_count) {
	this.comment_count = comment_count;
}
public int getLike_count() {
	return like_count;
}
public void setLike_count(int like_count) {
	this.like_count = like_count;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
}
