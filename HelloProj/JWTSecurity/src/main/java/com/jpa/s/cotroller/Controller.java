package com.jpa.s.cotroller;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
//import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

//import email.EmailService;
//import com.jpa.s.cotroller.EmailService;
import com.jpa.s.dto.Dto;
import com.jpa.s.entity.MyOTP;
import com.jpa.s.entity.Product;
import com.jpa.s.entity.UserInfo;
import com.jpa.s.repo.mongorepo;
import com.jpa.s.repo.myotprepo;
import com.jpa.s.service.JWTService;
import com.jpa.s.service.Servvice;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/pro")
public class Controller {
	@Autowired
	RestTemplate rt;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private Servvice service;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
    @Autowired
    private mongorepo repos;
    
    @Autowired
    private myotprepo otprepo;
    
	@Autowired
	private EmailService emailService;
	
	
	//This is for extraction of user from jwt
	@PreAuthorize("hasAuthority('ROLE_USER')") 
	 @GetMapping("/dummy/{tok}")
	 public String dummy(@PathVariable String tok) {
		// return "jdhjdh";
		 
		 return jwtService.extractUsername(tok);
	 }
	
	
    
	@GetMapping("/a")
	public String A() {
		return "a faltu";
	}
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/b")
	public String B() {
		return "b user";
	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/c")
	public String C() {
		return "c admin";
	}
    
    
	@PostMapping("/addUser")
	public ResponseEntity<?> newUser(@RequestBody UserInfo userInfo) {
		if(repos.findById(userInfo.getId()).orElse(null)!=null){
			 return new ResponseEntity<>("useralready",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
		return service.addUser(userInfo);
	    }
	}
	
	//added by me
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 @GetMapping("/search/{id}")
	 public ResponseEntity<UserInfo> getUserById(@PathVariable String id) {
	        Optional<UserInfo> userOptional = repos.findById(id);

	        if (userOptional.isPresent()) {
	        	UserInfo userInfo = new UserInfo();
	             userInfo = userOptional.get();
	            return new ResponseEntity<>(userInfo, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 @PutMapping("/makeAdmin/{id}")
	 public ResponseEntity<String> makeUserAdmin(@PathVariable String id) {
//		  Optional<UserInfo> userOptional = repos.findById(id);
		  
//	            return ResponseEntity.notFound().build();
//	        }
//	        userInfo.setRoles("ROLE_ADMIN");
//	        repos.save(userInfo);
//	        return ResponseEntity.ok("User has been made an admin.");
		  UserInfo userOptional = repos.findById(id).orElse(null);
		  if(userOptional==null) {
			  return ResponseEntity.notFound().build();
		  }
		  userOptional.setRoles("ROLE_ADMIN");
	        repos.save(userOptional);
	        return ResponseEntity.ok("User has been made an admin.");
	}
	 
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 @PutMapping("/revertAdmin/{id}")
	 public ResponseEntity<String> revertAdmin(@PathVariable String id) {
//		  Optional<UserInfo> userOptional = repos.findById(id);
		  
//	            return ResponseEntity.notFound().build();
//	        }
//	        userInfo.setRoles("ROLE_ADMIN");
//	        repos.save(userInfo);
//	        return ResponseEntity.ok("User has been made an admin.");
		  UserInfo userOptional = repos.findById(id).orElse(null);
		  if(userOptional==null) {
			  return ResponseEntity.notFound().build();
		  }
		  userOptional.setRoles("ROLE_USER");
	        repos.save(userOptional);
	        return ResponseEntity.ok("User has been made a user.");
	}
	 
	 
	 

//	@PostMapping("/otp/generate")
//	public ResponseEntity<?> getPlainOtp(@RequestBody MyOTP motpe) {
//	    Random rand = new Random();
//	    double rand_dub2 = rand.nextDouble();
//	    String myRanStr = String.valueOf(rand_dub2);
//	    myRanStr = myRanStr.substring(2, 8);
//	    motpe.setOtp(myRanStr);
//	    UserInfo uifo = repos.findById(motpe.getId()).orElse(null);
//	    if (uifo == null) {
//	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	    motpe.setTo(uifo.getEmail());
//	    otprepo.save(motpe);
//	    emailService.sendTextMail(motpe);
//
//	    return ResponseEntity.ok().build();
//	}
	 

	 
//navin	 
	 
//		@PostMapping("/otp/generate")
//		public ResponseEntity<?> getEncryptedOtp(@RequestBody MyOTP motpe) {
//		    try {
//		        // Generate random OTP
//		        Random rand = new Random();
//		        double rand_dub2 = rand.nextDouble();
//		        String myRanStr = String.valueOf(rand_dub2);
//		        myRanStr = myRanStr.substring(2, 8);
//
//		        // Encrypt the OTP
//		        String encryptedOtp = encryptOTP(myRanStr, "$C&F)J@NcRfUjXn2r4u7x!A%D*G-KaPd"); 
//
//		        // Save the encrypted OTP to the database
//		        motpe.setOtp(encryptedOtp);
//		        UserInfo uifo = repos.findById(motpe.getId()).orElse(null);
//		        if (uifo == null) {
//		            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		        }
//		        motpe.setTo(uifo.getEmail());
//		        otprepo.save(motpe);
//
//		        emailService.sendTextMail(motpe);
//
//		        return ResponseEntity.ok().build();
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		    }
//		}
	 
	 
//by deepak	
//for generating the otp
	@PostMapping("/otp/generate")
	public ResponseEntity<?> getEncryptedOtp(@RequestBody MyOTP motpe) {
	    try {
	        // Generate random OTP
	        Random rand = new Random();
	        double rand_dub2 = rand.nextDouble();
	        String myRanStr = String.valueOf(rand_dub2);
	        myRanStr = myRanStr.substring(2, 8);

	        // Encrypt the OTP
	        String encryptedOtp = encryptOTP(myRanStr, "$C&F)J@NcRfUjXn2r4u7x!A%D*G-KaPd");
	        //String encryptedOtp =myRanStr;

	        // Save the encrypted OTP to the database
	        motpe.setOtp(encryptedOtp);
	        UserInfo uifo = repos.findById(motpe.getId()).orElse(null);
	        if (uifo == null) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        motpe.setRoles(uifo.getRoles());
	        motpe.setTo(uifo.getEmail());
	        otprepo.save(motpe);
	        motpe.setOtp(myRanStr);

	        emailService.sendTextMail(motpe);

	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
// function for encrypting the otp
	private String encryptOTP(String otp, String key) throws Exception {
	    Key secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
	    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	    byte[] encryptedBytes = cipher.doFinal(otp.getBytes(StandardCharsets.UTF_8));
	    return Base64.getEncoder().encodeToString(encryptedBytes);
	}
	
//navin
//	@PostMapping("/otp/authenticate")
//	public ResponseEntity<?> authenticateByOtp(@RequestBody MyOTP motpe) {
//	    MyOTP motpetemp = otprepo.findById(motpe.getId()).orElse(null);
//	    if (motpetemp != null) {
//	        try {
//	            String decryptedOtp = decryptOTP(motpetemp.getOtp(), "$C&F)J@NcRfUjXn2r4u7x!A%D*G-KaPd"); 
//	            if (decryptedOtp.equals(motpe.getOtp())) {
//	                // Create a response object to return the empId
//	                Map<String, String> response = new HashMap<>();
//	                response.put("empId", motpetemp.getId()); // Assuming empId is a field in MyOTP
//
//	                return ResponseEntity.ok(response);
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    // If the authentication fails or OTP is incorrect, return an error response
//	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//	}
	
//deepak
@PostMapping("/otp/authenticate")
public Map<String,String> authenticateByOtp(@RequestBody MyOTP motpe ) {
		Dto dto = new Dto();
		dto.setId(motpe.getId());
		dto.setPwd(motpe.getOtp());
		Map<String,String> mp =new HashMap<String,String>();
		UserInfo uifo=repos.findById(dto.getId()).orElse(null);
		uifo.setPassword(passwordEncoder.encode(dto.getPwd()));
		repos.save(uifo);
		MyOTP motpetemp=otprepo.findById(dto.getId()).orElse(null);
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getId(), dto.getPwd()));
		String decryptedOtp="";
        try {
			decryptedOtp = decryptOTP(motpetemp.getOtp(), "$C&F)J@NcRfUjXn2r4u7x!A%D*G-KaPd");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(motpetemp!=null && decryptedOtp.equals(dto.getPwd()) && authentication.isAuthenticated()) {
		    //return new ResponseEntity<>(null, HttpStatus.OK);
			mp.put("token", jwtService.generateToken(dto.getId()));
			mp.put("empId", motpetemp.getId());
			return mp;
		}
		else {
		    //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			throw new UsernameNotFoundException("user not found");
		}
}

	
	



//function for decrypting the otp

private String decryptOTP(String encryptedOtp, String key) throws Exception {
    Key secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decodedBytes = Base64.getDecoder().decode(encryptedOtp);
    byte[] decryptedBytes = cipher.doFinal(decodedBytes);
    return new String(decryptedBytes, StandardCharsets.UTF_8);
}


//Do for rest template and implement logics
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@PutMapping("/approveProductd/{productId}")
public ResponseEntity<String> approveProduct(@PathVariable String productId)
{
	rt.getForObject("http://localhost:8082/User/approveProductd/"+productId, String.class);
	return ResponseEntity.ok("Product approved successfully");
	//return "hdjhd";
}

@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@PutMapping("/deletePostd/{productId}")
public String deleteUserPost(@PathVariable String productId)
{
	String s=rt.getForObject("http://localhost:8082/User/deletePostd/"+productId, String.class);
	return s;
	//return "hdjhd";
}

//modify this to implement all functionality
//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
@PutMapping("/deleteAuth/{productId}/{empid}/{jwttok}")
public String deleteAuth(@PathVariable String productId,@PathVariable String empid,@PathVariable String jwttok) {
	String id=jwtService.extractUsername(jwttok);
	UserInfo uifo=repos.findById(id).orElse(null);
	String role=uifo.getRoles();
	if(role.equals("ROLE_ADMIN")) {
		String s=rt.getForObject("http://localhost:8082/User/deletePostd/"+productId, String.class);
	}
	else {
		String s=rt.getForObject("http://localhost:8082/User/deletePostdu/"+productId+"/"+empid, String.class);
	}
	return "deleted";
}



//
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//@GetMapping("/deletePostd/{productId}")
//public String deleteUserPostd(@PathVariable("productId") String productId) {
//{
//	String s=rt.getForObject("http://localhost:8082/User/deletePost/"+productId, String.class);
//	return s;
//	//return "hdjhd";
//}	
	
	


}
