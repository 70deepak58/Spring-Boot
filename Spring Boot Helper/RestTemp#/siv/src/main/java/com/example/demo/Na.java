package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class Na {
@Autowired
private RestTemplate rt;
@RequestMapping("deep")
@ResponseBody
public String dop() {
	String s=rt.getForObject("http://localhost:8081/sai", String.class);
	return "do"+s;
}
	
}
