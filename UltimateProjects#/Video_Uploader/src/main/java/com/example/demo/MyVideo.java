package com.example.demo;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Document("FP_video")
public class MyVideo {
	@Id
	public long id;
	public String tiltle;
	public Binary video;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTiltle() {
		return tiltle;
	}
	public void setTiltle(String tiltle) {
		this.tiltle = tiltle;
	}
	public Binary getVideo() {
		return video;
	}
	public void setVideo(Binary video) {
		this.video = video;
	}

}
