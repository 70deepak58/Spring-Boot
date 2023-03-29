package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document("COMMENT")
public class Comment {
@Id
public int id;
public String comment;
public int message_id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public int getMessage_id() {
	return message_id;
}
public void setMessage_id(int message_id) {
	this.message_id = message_id;
}

}

