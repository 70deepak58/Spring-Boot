package com.jpa.s.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jpa.s.entity.UserInfo;
import com.jpa.s.repo.mongorepo;
import com.jpa.s.entity.MyOTP;
import com.jpa.s.repo.myotprepo;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private mongorepo repo;
	@Autowired
	private myotprepo mrepo;
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<UserInfo>userInfo=repo.findById(id);
		return userInfo.map(UserInfoUserDetails::new)
		        .orElseThrow(()->new UsernameNotFoundException("User Not Found"+id));
	
	}
//	@Override
//	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//		Optional<MyOTP>otpInfo=mrepo.findById(id);
//		return otpInfo.map(UserInfoUserDetails::new)
//		        .orElseThrow(()->new UsernameNotFoundException("User Not Found"+id));
//	
//	}

}
