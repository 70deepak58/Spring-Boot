package com.jpa.s.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jpa.s.entity.UserInfo;
import com.jpa.s.repo.mongorepo;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private mongorepo repo;
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Optional<UserInfo>userInfo=repo.findByName(id);
		return userInfo.map(UserInfoUserDetails::new)
		        .orElseThrow(()->new UsernameNotFoundException("User Not Found"+id));
	
	}

}
