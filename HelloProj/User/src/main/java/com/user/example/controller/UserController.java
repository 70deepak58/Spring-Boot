package com.user.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.example.entity.Product;
import com.user.example.entity.User;
import com.user.example.repo.ProductInterface;
import com.user.example.userService.UserService;

@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@Autowired 
	private ProductInterface productInterface;
	
	  


//product listing page
	@GetMapping("/getonlyTrue")
	public List<Map<String, String>> getonlyAll() {
	    List<Map<String, String>> productList = new ArrayList<>();
	    List<Product> products = userService.getAllTrue();

	    for (Product product : products) {
	        String firstImage = "";
	        if (!product.getImage().isEmpty()) {
	            Binary binaryImage = product.getImage().get(0);
	            byte[] imageData = binaryImage.getData(); // Assuming there's a method to retrieve the image data

	            // Convert the byte array to Base64 encoded string
	            String base64Image = Base64.getEncoder().encodeToString(imageData);

	            // Construct the image URL from the base64 encoded string
	            String imageUrl = "data:image/png;base64," + base64Image;

	            firstImage = imageUrl;
	        }

	        Map<String, String> productInfo = new HashMap<>();
	        productInfo.put("productName", product.getProductName());
	        productInfo.put("empId", product.getEmpId());
	        productInfo.put("image", firstImage);
            productInfo.put("productId",product.getProductId() );
	        productList.add(productInfo);
	    }

	    return productList;
	}
	
	//product detail page
	
	@GetMapping("/getProduct/{productId}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable String productId) {
        try {
        	
            // Fetch the product details based on the productId
            Product product = userService.getProduct(productId);
          
            // Check if the product exists
            if (product == null) {
                return ResponseEntity.notFound().build();
            }

            // Prepare the response
            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("productName", product.getProductName());
            productInfo.put("productId", product.getProductId());
            productInfo.put("mobileNumber", product.getMobileNumber());
            productInfo.put("description", product.getDescription());
            productInfo.put("empId", product.getEmpId());
            productInfo.put("ownerName", product.getOwnerName());
            productInfo.put("price", product.getPrice());

            // Handle image data
            List<String> images = new ArrayList<>();
            for (Binary binaryImage : product.getImage()) {
                byte[] imageData = binaryImage.getData();
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                String imageUrl = "data:image/png;base64," + base64Image;
                images.add(imageUrl);
            }
            productInfo.put("image", images);

            // Handle video data
            List<String> videos = new ArrayList<>();
            for (Binary binaryVideo : product.getVideo()) {
                byte[] videoData = binaryVideo.getData();
                String base64Video = Base64.getEncoder().encodeToString(videoData);
                String videoUrl = "data:video/mp4;base64," + base64Video;
                videos.add(videoUrl);
            }
            productInfo.put("video", videos);

            // Handle comments
            List<String> comments = product.getComment();
            productInfo.put("comments", comments);

            return ResponseEntity.ok(productInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


//product post page

	@PostMapping("/upload")
	public Product uploadPhoto(@RequestParam("image") MultipartFile[] images,
	                           @RequestParam("video") MultipartFile[] videos,
	                           @RequestParam("empId") String empId,
	                           @RequestParam("ownerName") String ownerName,
	                           @RequestParam("productName") String productName,
	                           @RequestParam("price") int price,
	                           @RequestParam("description") String description,
	                           @RequestParam("advType") String advType,
	                           @RequestParam("mobileNumber") String mobileNumber) throws IOException {

	    Product mi = new Product();
//commented by deepak
	    // Set product id
//	    Double temp = Math.random();
//	    String temp1 = temp.toString();
//	    String temp2 = temp1.substring(2, 7);
//	    mi.setProductId(temp2);

	    List<Binary> img = new ArrayList<>();
	    for (MultipartFile image : images) {
	        img.add(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
	    }

	    List<Binary> vdo = new ArrayList<>();
	    for (MultipartFile video : videos) {
	        vdo.add(new Binary(BsonBinarySubType.BINARY, video.getBytes()));
	    }

	    mi.setEmpId(empId);
	    mi.setImage(img);
	    mi.setVideo(vdo);
	    mi.setOwnerName(ownerName);
	    mi.setDescription(description);
	    mi.setPrice(price);
	    mi.setProductName(productName);
	    mi.setMobileNumber(mobileNumber);

	    productInterface.save(mi);
	    return mi;
	}

//for update product
	   @PutMapping("/updateProduct/{productId}")
	    public Product updateProduct(@PathVariable("productId") String productId,
	                                 @RequestParam(value="image", required=false) MultipartFile[] images,
	                                 @RequestParam(value="video", required=false) MultipartFile[] videos,
	                                 @RequestParam("empId") String empId,
	                                 @RequestParam("ownerName") String ownerName,
	                                 @RequestParam("productName") String productName,
	                                 @RequestParam("price") int price,
	                                 @RequestParam("description") String description,
	                                 
	                                 @RequestParam("mobileNumber") String mobileNumber) throws IOException {
		   //, required=false
		   //, required=false

	        Optional<Product> optionalProduct = productInterface.findById(productId);
	        if (optionalProduct.isEmpty()) {
	        	//handle product not found
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
	        }

	        Product mi = optionalProduct.get();

	        //image logic here
	        if(images==null) {
	        	mi.setImage(optionalProduct.get().getImage());
	        	
	        }
	        else {
		        List<Binary> img = new ArrayList<>();
		        for (MultipartFile image : images) {
		            img.add(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
		        }
		        mi.setImage(img);
	        }

	        //video logic here
            if(videos==null) {
            	mi.setVideo(optionalProduct.get().getVideo());
            	
            }
            else {
    	        List<Binary> vdo = new ArrayList<>();
    	        for (MultipartFile video : videos) {
    	            vdo.add(new Binary(BsonBinarySubType.BINARY, video.getBytes()));
    	        }
    	        mi.setVideo(vdo);
            }

	        
	        mi.setApprove(false);
	        mi.setEmpId(empId);
	        mi.setOwnerName(ownerName);
	        mi.setDescription(description);
	        mi.setPrice(price);
	        mi.setProductName(productName);
//	        if(mi.equals(optionalProduct)) {
//	        	return mi;
//	        }
        	productInterface.save(mi);
	        return mi;
	    }
	
	// get only false product  for admin page

	@GetMapping("/getAllFalse")
	public List<Map<String, String>> getOnlyFalseProducts() {
	    List<Map<String, String>> productList = new ArrayList<>();
	    List<Product> products = userService.getAllFalse();

	    for (Product product : products) {
	        String firstImage = "";
	        if (!product.getImage().isEmpty()) {
	            Binary binaryImage = product.getImage().get(0);
	            byte[] imageData = binaryImage.getData(); // Assuming there's a method to retrieve the image data

	            // Convert the byte array to Base64 encoded string
	            String base64Image = Base64.getEncoder().encodeToString(imageData);

	            // Construct the image URL from the base64 encoded string
	            String imageUrl = "data:image/png;base64," + base64Image;

	            firstImage = imageUrl;
	        }

	        Map<String, String> productInfo = new HashMap<>();
	        productInfo.put("productName", product.getProductName());
	        productInfo.put("empId", product.getEmpId());
	        productInfo.put("image", firstImage);
	        productInfo.put("productId", product.getProductId());
	        productList.add(productInfo);
	    }

	    return productList;
	}

	

	
	 @PutMapping("/updateUserPost/{productId}")
	 public Product uploadPhotos( @RequestParam("image") MultipartFile[] images,@RequestParam("video") MultipartFile[] videos, @RequestParam("data") String str,  @PathVariable("productId") String productId ) throws IOException {
		 Optional<Product> tempProduct = productInterface.findById(productId);
	
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

			mi.setProductId(productId);
			productInterface.save(mi);
			return mi;
		 }
		 
		 return null;
		}
	 
	 
	 
	 //Below 4 function and only two logical code
	 //approval of product by admin using jwt
	 @GetMapping("/approveProductd/{productId}")
	 public ResponseEntity<String> approveProductd(@PathVariable String productId) {
	     Optional<Product> optionalProduct = productInterface.findById(productId);
	     
	     if (optionalProduct.isPresent()) {
	         Product product = optionalProduct.get();
	         product.setApprove(true);
	         productInterface.save(product);
	         
	         return ResponseEntity.ok("Product approved successfully");
	     } else {
	         return ResponseEntity.notFound().build();
	     }
	 }
		//for deleting post both by admin or user using jwt
		@GetMapping("/deletePostd/{productId}")
		public String deleteUserPostd(@PathVariable("productId") String productId) {
		    return userService.deletePost(productId);
		}
		
		@GetMapping("/deletePostdu/{productId}/{empid}")
		public String deleteUserPostdu(@PathVariable("productId") String productId,@PathVariable("empid") String empid) {
		    return userService.deletePostdu(productId,empid);
		}
	 
		
		
//**********************************************************************
		 ///    ////        //////////           ////////////////////
//**********************************************************************		
	 //Wrong logic below right logic above with rest template
		//for deleting post both by admin or user without verification
		@DeleteMapping("/deletePost/{productId}")
		public String deleteUserPost(@PathVariable("productId") String productId) {
		    return userService.deletePost(productId);
		}


	 //approval of product by admin without verification
	 @PutMapping("/approveProduct/{productId}")
	 public ResponseEntity<String> approveProduct(@PathVariable String productId) {
	     Optional<Product> optionalProduct = productInterface.findById(productId);
	     
	     if (optionalProduct.isPresent()) {
	         Product product = optionalProduct.get();
	         product.setApprove(true);
	         productInterface.save(product);
	         
	         return ResponseEntity.ok("Product approved successfully");
	     } else {
	         return ResponseEntity.notFound().build();
	     }
	 }
	 

}
