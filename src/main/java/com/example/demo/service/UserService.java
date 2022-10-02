package com.example.demo.service;

import com.example.demo.model.PersonalContact;
import com.example.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

	public User getUserInfoByUserName(String userName);
	User addUser(User user);

	List<User> getAllUsers();

	public String getLoggedInUsername();

}