package com.example.demo;

import java.io.IOException;
import java.util.Base64;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/audio")
public class MyAudioController {
	@Autowired
	private MyAudioAdapter mva;
	@PostMapping("/upload")
	public String uploadAudio( @RequestParam("image") MultipartFile image) throws IOException {
		MyAudio mv= new MyAudio();
		mv.setAudio(
				new Binary(BsonBinarySubType.BINARY, image.getBytes())
				);
		mv.setId(mva.count()+1);
		mv.setTiltle("shdjsh");
		mva.insert(mv);
		
		return "jhjdhf";
	}
	@GetMapping("/download")
	public String downloadAudio(Model model) {
		
		MyAudio mv=mva.findById(mva.count()).orElse(null);
		//if(mi==null) return "nf";
		model.addAttribute("title", "dhgfhdg");
		model.addAttribute("image",Base64.getEncoder().encodeToString(mv.getAudio().getData()) );
		
		return Base64.getEncoder().encodeToString(mv.getAudio().getData());
	}
	
	
	/*
	 * @GetMapping("/photos/{id}")
public String getPhoto(@PathVariable String id, Model model) {
    Photo photo = photoService.getPhoto(id);
    model.addAttribute("title", photo.getTitle());
    model.addAttribute("image", 
      Base64.getEncoder().encodeToString(photo.getImage().getData()));
    return "photos";
}
	 */	

}
