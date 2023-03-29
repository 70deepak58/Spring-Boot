package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value="feignDemo2",url="http://localhost:8086/message")
public interface FeignLike {
	@PostMapping("/likeamessage")
	public String addLikeAMessage(String lst);

}
