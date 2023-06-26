package com.user.example;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.user.example.entity.User;
import com.user.example.userService.UserService;

@SpringBootTest

class UserApplicationTests {
   
//	@Autowired
//	private UserService userService1;
//	
//	@MockBean
//    private UserService userService;
//	
//	@Test
//	 public String signInTest() throws Exception {
////		        User user = new User();
////		        user.setEmp_id("123");
////		        user.setPwd("password");
//
//		User user = new User();
//
////		        user.setEmp_id("123");
////		        user.setPwd("password");
//		 user.setEmp_id("123");
//		 user.setPwd("password");
//		 when(userService.userSignIn(any(User.class), any(String.class), any(String.class))).thenReturn("Logged in successfully!");
//		 mockMvc.perform(post("/User/signIn/123/password").contentType("application/json").content("{ \"empId\": \"123\", \"password\": \"password\", \"name\": \"Test User\" }")).andExpect(status().isOk()).andExpect(jsonPath("$").value("Logged in successfully!"));
//
//		               
//
//		    }

}
