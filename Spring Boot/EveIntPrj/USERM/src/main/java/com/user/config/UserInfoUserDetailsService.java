package com.user.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.user.entity.UserInfo;
import com.user.repo.UserInfoRepo;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepo userInfoRepo;
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		//changed here to get by id instead of name to generate tokens
		//Optional<UserInfo>userInfo=repo.findByName(id);
		Optional<UserInfo>userInfo=userInfoRepo.findById(id);
		return userInfo.map(UserInfoUserDetails::new)
		        .orElseThrow(()->new UsernameNotFoundException("User Not Found"+id));
	
	}

}
