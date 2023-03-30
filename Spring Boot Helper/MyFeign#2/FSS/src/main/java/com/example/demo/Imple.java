package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/na")
public class Imple {
@Autowired
fs ff;
@GetMapping("/n-a")
public String Name() {
	return ff.N();
}
@GetMapping("/n-h")
public String Home() {
	return ff.H();
}
@GetMapping("/n-l")
public String Like() {
	return ff.L();
}

}
