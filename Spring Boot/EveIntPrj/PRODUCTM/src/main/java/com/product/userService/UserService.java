package com.product.userService;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.entity.User;
import com.product.entity.Product;
import com.product.repo.ProductRepo;
import com.product.repo.UserRepo;
//import com.user.example.repo.UserInterface;

@Service
public class UserService {
//	
//	@Autowired
//	private UserInterface userInterface;

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private UserRepo userRepo;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
//Final Signup Logic
	public User userSignUp(User user) {
		
	//http.csrf().disable();
//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		
//		String pwdEncode = passwordEncoder.encode(user.getPwd());
//		user.setPwd(pwdEncode);
//		String encodedString = Base64.getEncoder().withoutPadding().encodeToString(user.getPwd().getBytes());
//		user.setPwd(encodedString);
		String encodedString = Base64.getEncoder().withoutPadding().encodeToString(user.getPassword().getBytes());
		user.setPassword(encodedString);
		userRepo.save(user);
		return user;
	}
//	public ResponseEntity<User>getSignIn(String emp_id){
//		User user = new 
//	}
	public List<Product> getAllTrue(){
		List<Product> all_pro= productRepo.findAll();
		List<Product> tuue_pro=new ArrayList<Product>();
		for(int i=0;i<all_pro.size();i++) {
			if(all_pro.get(i).isApprove()) {
				tuue_pro.add(all_pro.get(i));
			}
		}
		
		return tuue_pro;
		//return all_pro;
	}
	
	public Product postAddFalse(Product product){
		
		
		//save employee id into product collection 
		User user= new User();
//		product.setEmp_id(user.getEmp_id());
		product.setEmp_id(user.getId());
		
		//Set product id 
		Double temp=Math.random();
    	String temp1=temp.toString();
    	String temp2=temp1.substring(2,7);
//		product.setProduct_id(temp2);
//		product.setImage(
//				new Binary(BsonBinarySubType.BINARY, image.getBytes()));
    	productRepo.save(product);
		return product;
	}
	
	
	public List<User> allUser() {
		return userRepo.findAll();
	}
	
	
//	public String userSignIn(String emp_id, String pwd) {
//		Optional<User> tempId= userInterface.findById(emp_id);
//		User usr = new User();
//		if(tempId.isPresent()) {
//			//String orginalPwd = 
//			        usr = tempId.get();
////					byte[] decodedBytes = Base64.getDecoder().decode(usr.getPwd());
//					byte[] decodedBytes = Base64.getDecoder().decode(usr.getPassword());
//					String decodedString = new String(decodedBytes);
//					if(pwd.equals(decodedString))
//					   return "Login";
//					else
//						return "wrong pass";		
//		}
//		return "wrong id";
//		
//		
//	    if(everything_fine) {
//        return new ResponseEntity<>(RestModel, HttpStatus.OK);
//    } else {
//        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//	}
	
	
	
	
public ResponseEntity<?> userSignIn(String emp_id, String pwd) {
	User tempUsr= userRepo.findById(emp_id).orElse(null);
	byte[] decodedBytes = Base64.getDecoder().decode(tempUsr.getPassword());
	String decodedString = new String(decodedBytes);
	if(tempUsr!=null && pwd.equals(decodedString)) {
	    return new ResponseEntity<>(null, HttpStatus.OK);
	}
	else {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
	
	
	
	
	
	
	
	
	
	
	
//	mi.setImage(
//			new Binary(BsonBinarySubType.BINARY, image.getBytes())
//			);
	public String deletePost(Product product, ObjectId product_id) {
		Optional<Product> tempProduct = productRepo.findById(product_id);
		
		Product copyProduct = new Product();
		if(tempProduct.isPresent()) {
			copyProduct = tempProduct.get();
			productRepo.delete(copyProduct);
			return "Deleted";
			
		}
		return "Wrong Product Id";
	}
	/*{
        "owner_name":"NITESH",
		"product_name":"home",
		"price":123,
		"mobile_no":"+9199999234",
		"discription":"awesome",
		"adv_type":true
}
*/
	public Product updatePost(Product product, ObjectId product_id) {
Optional<Product> tempProduct = productRepo.findById(product_id);
		
		Product products = new Product();
		if(tempProduct.isPresent()) {
			products = tempProduct.get();
		products.setOwner_name(product.getOwner_name());
		products.setProduct_name(product.getProduct_name());
		products.setPrice(product.getPrice());
		products.setMobile_no(product.getMobile_no());
		products.setDiscription(product.getDiscription());
		products.setAdv_type(product.isAdv_type());
		productRepo.save(products);
		return products;
		}
		
		return null;
	}
	public String userComment(Product product, ObjectId product_id, String emp_id ,String commnt) {
		Optional<Product> tempProduct = productRepo.findById(product_id);
		Product products = new Product();
		products = tempProduct.get();
		
		
		List <String> temp = new ArrayList<String>();
		temp = products.getComment();
		
		String username = "";
		
		
		Optional<User> tempUser = userRepo.findById(emp_id);
		User user = new User();
		user = tempUser.get();
		
//		username = user.getName();
		username = user.getUsername();
		
		temp.add(username);
		temp.add(commnt);
		
		products.setComment(temp);
		productRepo.save(products);
		return commnt;
	}
}
