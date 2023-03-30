package com.example.demo;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class a1 {
	@RequestMapping("/a")
	@ResponseBody
	public String p1() {
		return "from home";
	}

}
