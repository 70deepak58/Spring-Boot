package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value="feignDemo",url="http://localhost:8086/message")
public interface FeignAdapter {
	@GetMapping("/getallmessage")
	public String getMessages();
	@PostMapping("/commentonpost")
	public String addingCommentOnMessage(String lst);
}
