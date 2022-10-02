package com.example.demo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.example.demo.model.User user = userService.getUserInfoByUserName(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
		return new User(user.getPhoneNumber(), user.getPassword(), Arrays.asList(authority));
	}
}
