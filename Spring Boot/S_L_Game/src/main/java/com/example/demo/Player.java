package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="SLG")
public class Player {
	@Id
	public int id;
	public String name;
	public String pass;
	public int status=-1;
	public int score;
	public int turn=0;
	public String msg="initial";
	
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getPass() {
		return this.pass;
	}
	public int getStatus() {
		return this.status;
	}
	public int getScore() {
		return this.score;
	}
	public int getTurn() {
		return this.turn;
	}
	public String getMsg() {
		return this.msg;
	}
	
	
	
	
	
	public void setId(int i) {
		this.id=i;
	}
	public void setName(String na) {
		this.name=na;
	}
	public void setPass(String pas) {
		this.pass=pas;
	}
	public void setStatus(int stat) {
		this.status=stat;
	}
	public void setScore(int sco) {
		this.score=sco;;
	}
	public void setTurn(int tu) {
		this.turn=tu;;
	}
	public void setMsg(String m) {
		this.msg=m;;
	}

}
