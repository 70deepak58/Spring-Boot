package com.jpa.s.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.s.dto.Dto;
import com.jpa.s.entity.Product;
import com.jpa.s.entity.UserInfo;
import com.jpa.s.service.JWTService;
import com.jpa.s.service.Servvice;

@RestController
@RequestMapping("/pro")
public class Controller {

	@Autowired
	private Servvice service;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/first")
	public String fun1() {
		return "Hello Avinash";
	}
	
	
	@GetMapping("/second")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String fun2() {
		return "Hello Rahul";
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Product>getAllProduct(){
		return service.getProduct();
	}
	
	
	@GetMapping("/getall")
	public List<UserInfo>getall(){
		return service.getuser();
	}
	
	@PostMapping("/addUser")
	public String newUser(@RequestBody UserInfo userInfo) {
		return service.addUser(userInfo);
	}
	
	
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody Dto dto) {

		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getId(), dto.getPwd()));
				if(authentication.isAuthenticated()) {
					return jwtService.generateToken(dto.getId());
				}else {
					throw new UsernameNotFoundException("user not found");
					}
		
		
	}
	
	@DeleteMapping("/delete")
	public String deleteItems() {
		return service.del();
	
	}
}
