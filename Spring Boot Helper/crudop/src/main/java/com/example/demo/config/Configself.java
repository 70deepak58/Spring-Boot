package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Configself {
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
      SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
		  http.csrf().disable()
		  .authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
    	  return http.build();
      }
	@Bean
	public UserDetailsService userDetailService() {
		UserDetails usr=User.builder()
				.username("Deepak")
				.password(passwordEncoder().encode("123456"))
				.roles("USER")
				.build();
		
		
		UserDetails admin=User.builder()
				.username("ASD")
				.password(passwordEncoder().encode("ASD"))
				.roles("ADMIN")
				.build();
		
		
		return new InMemoryUserDetailsManager(usr,admin);
	}
}
