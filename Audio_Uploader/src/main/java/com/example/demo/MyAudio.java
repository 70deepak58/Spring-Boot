package com.example.demo;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document("FP_audio")
public class MyAudio {
	@Id
	public long id;
	public String tiltle;
	public Binary audio;
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
	public Binary getAudio() {
		return audio;
	}
	public void setAudio(Binary audio) {
		this.audio = audio;
	}

}
