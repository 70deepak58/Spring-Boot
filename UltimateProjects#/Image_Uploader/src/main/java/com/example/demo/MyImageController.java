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
@RequestMapping("/img")
public class MyImageController {
	@Autowired
	private MyImageAdapter mia;
	@PostMapping("/upload")
	public String uploadPhoto( @RequestParam("image") MultipartFile image) throws IOException {
		MyImage mi= new MyImage();
		mi.setImage(
				new Binary(BsonBinarySubType.BINARY, image.getBytes())
				);
		mi.setId(mia.count()+1);
		mi.setTiltle("shdjsh");
		mia.insert(mi);
		
		return "jhjdhf";
	}
	@GetMapping("/download")
	public String downloadPhoto(Model model) {
		
		MyImage mi=mia.findById(mia.count()).orElse(null);
		//if(mi==null) return "nf";
		model.addAttribute("title", "dhgfhdg");
		model.addAttribute("image",Base64.getEncoder().encodeToString(mi.getImage().getData()) );
		
		return Base64.getEncoder().encodeToString(mi.getImage().getData());
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
