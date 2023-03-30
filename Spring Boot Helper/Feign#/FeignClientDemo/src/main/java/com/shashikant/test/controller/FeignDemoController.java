package com.shashikant.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shashikant.test.util.FeignUtilService;

@RestController
@RequestMapping("/demo")
public class FeignDemoController {
	
	@Autowired
	private FeignUtilService feignUtilService;
	
	@GetMapping("/user-name")
	public String getUserName() {
		return feignUtilService.getName();
	}
	
	@GetMapping("/user-address")
	public String getUserAddress() {
		return feignUtilService.getAddress();
	}
	 
	@GetMapping("/user-status")
	public String getUserStatus() {
		return feignUtilService.getStatus();
	}
}
