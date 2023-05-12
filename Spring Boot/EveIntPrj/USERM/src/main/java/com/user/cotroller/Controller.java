package com.user.cotroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.Dto;
import com.user.entity.MyOTP;
import com.user.entity.Product;
import com.user.entity.UserInfo;
import com.user.service.JWTService;
import com.user.service.Servvice;
import com.user.repo.MyOTPRepo;
import com.user.repo.UserInfoRepo;

@RestController
@RequestMapping("/pro")
public class Controller {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
	private UserInfoRepo userInfoRepo;
    
    @Autowired
    private MyOTPRepo myOTPRepo;
    
	@Autowired
	private EmailService emailService;

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
	//testing user role
	@GetMapping("/second")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Map<String,String> fun2() {
		Map<String,String> mp =new HashMap<String,String>();
		mp.put("role", "user");
		return mp;
	}
	//testing admin role
	@GetMapping("/second2")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Map<String,String> fun3() {
		Map<String,String> mp =new HashMap<String,String>();
		mp.put("role", "admin");
		return mp;
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
	public Map<String,String> authenticateAndGetToken(@RequestBody Dto dto) {
		Map<String,String> mp =new HashMap<String,String>();

		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getId(), dto.getPwd()));
				if(authentication.isAuthenticated()) {
					mp.put("token", jwtService.generateToken(dto.getId()));
					return mp;
				}else {
					throw new UsernameNotFoundException("user not found");
					}
		
		
	}
	
	
	@PostMapping("/otp/generate")
	public String getPlainOtp(@RequestBody MyOTP motpe)  {
		Random rand = new Random();
		double rand_dub2 = rand.nextDouble();
	    String myRanStr=String.valueOf(rand_dub2);
	    myRanStr=myRanStr.substring(2, 8);
	    motpe.setOtp(myRanStr);
	    UserInfo uifo=userInfoRepo.findById(motpe.getId()).orElse(null);
	    if(uifo==null) {
	    	return "not found";
	    }
	    motpe.setTo(uifo.getEmail());
	    myOTPRepo.save(motpe);
		emailService.sendTextMail(motpe);	
		return "o";
	}
	
//	@PostMapping("/otp/authenticate")
//	public ResponseEntity<?> authenticateByOtp( @RequestBody MyOTP motpe) {
//		MyOTP motpetemp=otprepo.findById(motpe.getId()).orElse(null);
//		if(motpetemp!=null && motpetemp.getOtp().equals(motpe.getOtp())) {
//		    return new ResponseEntity<>(null, HttpStatus.OK);
//		}
//		else {
//		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	@PostMapping("/otp/authenticate")
	public Map<String,String> authenticateByOtp(@RequestBody Dto dto ) {
		Map<String,String> mp =new HashMap<String,String>();
		UserInfo uifo=userInfoRepo.findById(dto.getId()).orElse(null);
		uifo.setPassword(passwordEncoder.encode(dto.getPwd()));
		userInfoRepo.save(uifo);
		MyOTP motpetemp=myOTPRepo.findById(dto.getId()).orElse(null);
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getId(), dto.getPwd()));


		if(motpetemp!=null && motpetemp.getOtp().equals(dto.getPwd()) && authentication.isAuthenticated()) {
		    //return new ResponseEntity<>(null, HttpStatus.OK);
			mp.put("token", jwtService.generateToken(dto.getId()));
			return mp;
		}
		else {
		    //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			throw new UsernameNotFoundException("user not found");
		}
	}
	
	
	
	@DeleteMapping("/delete")
	public String deleteItems() {
		return service.del();
	
	}
}
