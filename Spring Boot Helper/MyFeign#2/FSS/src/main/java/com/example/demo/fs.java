package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value="feignDemo",url="http://localhost:8081/f")
public interface fs {
	@GetMapping("/name")
	String N();
	
	@GetMapping("/home")
	String H();
	
	@GetMapping("/like")
	String L();
}
