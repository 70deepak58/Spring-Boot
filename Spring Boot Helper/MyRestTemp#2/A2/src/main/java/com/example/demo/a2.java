package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class a2 {
	@Autowired
	RestTemplate rt;
	@RequestMapping("/b")
	@ResponseBody
	public String p3() {
		String s=rt.getForObject("http://localhost:8081/a", String.class);
		return "work "+s;
	}
	

}
