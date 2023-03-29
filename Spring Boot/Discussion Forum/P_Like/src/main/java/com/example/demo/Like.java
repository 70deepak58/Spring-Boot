package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document("LIKE")
public class Like {
	@Id
	public int id;
	public int message_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

}
