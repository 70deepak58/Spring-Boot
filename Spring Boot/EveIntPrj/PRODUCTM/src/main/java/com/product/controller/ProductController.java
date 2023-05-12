package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.entity.Product;
import com.product.entity.User;
import com.product.repo.ProductRepo;
import com.product.userService.UserService;


	//@CrossOrigin(origins = "*")
	@RestController
	@RequestMapping("/User")
	public class ProductController {

		@Autowired
		private UserService userService;
		
		
		@Autowired 
		private ProductRepo productRepo;
		
		//Final SignUP logic
		@CrossOrigin(origins = "*")
		@PostMapping("/signUp")
		public User signUp(@RequestBody User user){
		    return userService.userSignUp(user);
		}
		
		@PutMapping("/comment/{product_id}/{emp_id}/{commnt}")
		public String comment(@RequestBody Product product, @PathVariable("product_id") ObjectId product_id , @PathVariable("emp_id") String emp_id , @PathVariable("commnt") String commnt) {
			
			return userService.userComment(product, product_id, emp_id, commnt);
		}
		
		//Final SignIn Logic
//		@CrossOrigin(origins = "*")
//		@PostMapping("/signIn/{id}/{password}")
//		public String signIn( @PathVariable("id") String id, @PathVariable("password") String password ) {
//			return userService.userSignIn(id,password);
//			
//			
//			@RequestMapping(value="/rawdata/", method = RequestMethod.PUT)
//			public ResponseEntity<?> create(@RequestBody String data) {
//			    if(everything_fine) {
//			        return new ResponseEntity<>(RestModel, HttpStatus.OK);
//			    } else {
//			        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//			    }
//			}
//		}
		@CrossOrigin(origins = "*")
		@PostMapping("/signIn/{id}/{password}")
		public ResponseEntity<?> signIn( @PathVariable("id") String id, @PathVariable("password") String password ) {
			return userService.userSignIn(id,password);
		}
		
		
		
		
		
		
		
		
		
		
		@GetMapping("/getAllUser")
		public List<User> getAllUser(){
			return userService.allUser();
		}
		
		@GetMapping("/getAllTrue")
		public List<Product> getAll(){
			return userService.getAllTrue();
		}
		
//		@PostMapping("/uploadAdd")
//		public Product postAdd(@RequestBody Product product){
//			return userService.postAddFalse(product);
//		}
	//	
	//	
//		@PostMapping("/uploadD")
//		public Product postD(@RequestParam("data") String str){
//			Product product=null;
//			try {
//				product=new ObjectMapper().readValue(str, Product.class);
//				return userService.postAddFalse(product);
//			} catch (JsonProcessingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return userService.postAddFalse(product);
//		}
		
		
		@PostMapping("/upload")
		public Product uploadPhoto( @RequestParam("image") MultipartFile[] images,@RequestParam("video") MultipartFile[] videos, @RequestParam("data") String str) throws IOException {
			Product mi= new Product();
			
			
			try {
				mi=new ObjectMapper().readValue(str, Product.class);

			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			User user= new User();
			//user.setEmp_id(mi.getEmp_id());
			user.setId(mi.getEmp_id());
			//mi.setEmp_id(user.getEmp_id());
			mi.setEmp_id(user.getId());
			
			//Set product id 
			//Double temp=Math.random();
	    	//String temp1=temp.toString();
	    	//String temp2=temp1.substring(2,7);
			//mi.setProduct_id(temp2);
			List<Binary> img=new ArrayList<Binary>();
			for(MultipartFile image:images) {
				img.add(new Binary(BsonBinarySubType.BINARY,image.getBytes()));
			}
			List<Binary> vdo=new ArrayList<Binary>();
			for(MultipartFile video:videos) {
				vdo.add(new Binary(BsonBinarySubType.BINARY,video.getBytes()));
			}
			mi.setImage(img);
			mi.setVideo(vdo);
//			mi.setImage(
//					new Binary(BsonBinarySubType.BINARY, image )
//					);
			
//			mi.setVideo(
//					new Binary(BsonBinarySubType.BINARY, video.getBytes())
//					);
		
			
			productRepo.save(mi);
			return mi;
		}
		@DeleteMapping("/deletePost/{product_id}")
		public String deleteUserPost(@RequestBody Product product, @PathVariable("product_id") ObjectId product_id ){
			return userService.deletePost(product, product_id);
		}
		
		
		@PutMapping("/updatePost/{product_id}")
		public Product updateUserPost(@RequestBody Product product, @PathVariable("product_id") ObjectId product_id) {
			return userService.updatePost(product,product_id);
		}
		/*
		@PostMapping("/upload")
		public String uploadPhoto( @RequestParam("image") MultipartFile image) throws IOException {
			Product mi= new Product();
			User user= new User();
			mi.setEmp_id(user.getEmp_id());
			
			//Set product id 
			Double temp=Math.random();
	    	String temp1=temp.toString();
	    	String temp2=temp1.substring(2,7);
			mi.setProduct_id(temp2);
			mi.setImage(
					new Binary(BsonBinarySubType.BINARY, image.getBytes())
					);
			
//			mi.setVideo(
//					new Binary(BsonBinarySubType.BINARY, video.getBytes())
//					);
		
			
			productInterface.save(mi);
			return "jhjdhf";
		}
		*/
		
		 @PutMapping("/updateUserPost/{product_id}")
		 public Product uploadPhotos( @RequestParam("image") MultipartFile[] images,@RequestParam("video") MultipartFile[] videos, @RequestParam("data") String str,  @PathVariable("product_id") ObjectId product_id ) throws IOException {
			 Optional<Product> tempProduct = productRepo.findById(product_id);
		
			 if(tempProduct.isPresent()) {
			 Product mi= new Product();
				mi = tempProduct.get();
				
				try {
					mi=new ObjectMapper().readValue(str, Product.class);

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				//Set product id 
				
				List<Binary> img=new ArrayList<Binary>();
				for(MultipartFile image:images) {
					img.add(new Binary(BsonBinarySubType.BINARY,image.getBytes()));
				}
				List<Binary> vdo=new ArrayList<Binary>();
				for(MultipartFile video:videos) {
					vdo.add(new Binary(BsonBinarySubType.BINARY,video.getBytes()));
				}
				mi.setImage(img);
				mi.setVideo(vdo);
//				mi.setImage(
//						new Binary(BsonBinarySubType.BINARY, image )
//						);
				
//				mi.setVideo(
//						new Binary(BsonBinarySubType.BINARY, video.getBytes())
//						);
			
				///mi.setProduct_id(product_id);
				mi.setId(product_id);
				productRepo.save(mi);
				return mi;
			 }
			 
			 return null;
			}

	}
