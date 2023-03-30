package com.example.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Document("MSG")
public class Message {
	@Id
	public int id;
	public int user_id;
	public String msg;
	public int cmt_cnt;
	public int like_cnt;
	public Map<Integer,String> cmt_map= new HashMap<Integer,String>();
	public Set<Integer> like_set = new HashSet<Integer> ();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCmt_cnt() {
		return cmt_cnt;
	}
	public void setCmt_cnt(int cmt_cnt) {
		this.cmt_cnt = cmt_cnt;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public Map<Integer, String> getCmt_map() {
		return cmt_map;
	}
	public void setCmt_map(Map<Integer, String> cmt_map) {
		this.cmt_map = cmt_map;
	}
	public Set<Integer> getLike_set() {
		return like_set;
	}
	public void setLike_set(Set<Integer> like_set) {
		this.like_set = like_set;
	}
	
	
/*	@Id
	public int id;
	public int cmt_cnt=0;
	public int like_cnt=0;
	public Map<Integer,String> cmt_map= new HashMap<Integer,String>();
	public Set<Integer> like_set = new HashSet<Integer> ();
	public int recent_id;
	public String recent_msg;
	
	public int getId() {
		return id;
	}
	public int getCmt_Cnt() {
		return cmt_cnt;
	}
	public void setCmt_Cnt(int cmt_cnt) {
		this.cmt_cnt = cmt_cnt;
	}
	public Set<Integer> getLike_Set() {
		return like_set;
	}
	public void setLikeset(Set<Integer> like_set) {
		this.like_set = like_set;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLike_Cnt() {
		return like_cnt;
	}
	public void setLike_Cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public Map<Integer, String> getCmt_Map() {
		return cmt_map;
	}
	public void setCmt_Map(Map<Integer, String> cmt_map) {
		this.cmt_map = cmt_map;
	}
	public int getRecent_Id() {
		return recent_id;
	}
	public void setRecent_Id(int recent_id) {
		this.recent_id = recent_id;
	}
	public String getRecent_Msg() {
		return recent_msg;
	}
	public void setRecent_Msg(String recent_msg) {
		this.recent_msg = recent_msg;
	}
	
	*/

}
