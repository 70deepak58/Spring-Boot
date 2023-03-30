package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/f")
public class Fm {

	@GetMapping("/name")
	public String N() {
		return "deepak";
	}
	@GetMapping("/home")
	public String H() {
		return "Bihar";
	}
	@GetMapping("/like")
	public String L() {
		return "Cric";
	}
}
