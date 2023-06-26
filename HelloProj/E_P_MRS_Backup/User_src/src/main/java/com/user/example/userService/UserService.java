package com.user.example.userService;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.example.entity.User;
import com.user.example.entity.Comment;
import com.user.example.entity.Product;
import com.user.example.repo.CommentInterface;
import com.user.example.repo.ProductInterface;
import com.user.example.repo.UserInterface;
//import com.user.example.repo.UserInterface;

@Service
public class UserService {
//	
//	@Autowired
//	private UserInterface userInterface;
	@Autowired
	private CommentInterface commentInterface;

	@Autowired
	private ProductInterface productInterface;
	
	@Autowired
	private UserInterface userInterface;
	

	public User userSignUp(User user) {
	
		
		userInterface.save(user);
		return user;
	}
	public Product getProduct(String productId) {
	    Optional<Product> productOptional = productInterface.findById(productId);
	    return productOptional.orElse(null);
	}

	public List<Product> getAllTrue(){
		List<Product> allProducts= productInterface.findAll();
		List<Product> trueProducts=new ArrayList<Product>();
		for(int i=0;i<allProducts.size();i++) {
			if(allProducts.get(i).isApprove()) {
				trueProducts.add(allProducts.get(i));
			}
		}
		
		return trueProducts;
		
	}
	//get false products
	public List<Product> getAllFalse() {
	    List<Product> allProducts = productInterface.findAll();
	    List<Product> falseProducts = new ArrayList<>();

	    for (Product product : allProducts) {
	        if (!product.isApprove()) {
	            falseProducts.add(product);
	        }
	    }

	    return falseProducts;
	}

	public Product postAddFalse(Product product){
		User user= new User();
		product.setEmpId(user.getId());
		Double temp=Math.random();
    	String temp1=temp.toString();
    	String temp2=temp1.substring(2,7);
		product.setProductId(temp2);
		productInterface.save(product);
		return product;
	}
	


	
	public List<User> allUser() {
		return userInterface.findAll();
	}
	
	

	
//for deleting post
	public String deletePost(String productId) {
	    Optional<Product> tempProduct = productInterface.findById(productId);
	    
	    if (tempProduct.isPresent()) {
	        Product product = tempProduct.get();
	        productInterface.delete(product);
	        //delete all comments of a post
	        List<Comment> cmt=commentInterface.findAll();
	        for(int i=0;i<cmt.size();i++) {
	        	if(productId.equals(cmt.get(i).getProduct_id())) {
	        		//commentInterface.delete(cmt.get(i).getId());
	        		commentInterface.deleteById(cmt.get(i).getId());
	        	}
	        }
	        return "Deleted";
	    }
	    
	    return "Wrong Product ID";
	}
	
	public String deletePostdu(String productId,String empid) {
	    Optional<Product> tempProduct = productInterface.findById(productId);
	    
	    if (tempProduct.isPresent() && empid.equals(tempProduct.get().getEmpId())) {
	        Product product = tempProduct.get();
	        productInterface.delete(product);
	        //delete all comments of a post
	        List<Comment> cmt=commentInterface.findAll();
	        for(int i=0;i<cmt.size();i++) {
	        	if(productId.equals(cmt.get(i).getProduct_id())) {
	        		//commentInterface.delete(cmt.get(i).getId());
	        		commentInterface.deleteById(cmt.get(i).getId());
	        	}
	        }
	        return "Deleted";
	    }
	    
	    return "Wrong Product ID";
	}


	public Product updatePost(Product product, String product_id) {
Optional<Product> tempProduct = productInterface.findById(product_id);
		
		Product products = new Product();
		if(tempProduct.isPresent()) {
			products = tempProduct.get();
		products.setOwnerName(product.getOwnerName());
  	products.setProductName(product.getProductName());
		products.setPrice(product.getPrice());
		products.setMobileNumber(product.getMobileNumber());
		products.setDescription(product.getDescription());
		products.setAdvType(product.getAdvType());
		productInterface.save(products);
		return products;
		}
		
		return null;
	}
	
	public String userComment(Product product, String productId, String empId ,String comment) {
		Optional<Product> tempProduct = productInterface.findById(productId);
		Product products = new Product();
		products = tempProduct.get();
		List <String> temp = new ArrayList<String>();
		temp = products.getComment();
		String username = "";
		Optional<User> tempUser = userInterface.findById(empId);
		User user = new User();
		user = tempUser.get();
		username = user.getUsername();
		
		temp.add(username);
		temp.add(comment);
		
		products.setComment(temp);
		productInterface.save(products);
		return comment;
	}
}
