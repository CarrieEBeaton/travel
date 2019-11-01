package com.travel.demo.service;

import java.util.Collection;

import com.travel.demo.dto.User;

public interface UserServiceLayer {

	public User saveUser(User user);
	
	public User getUserById(Long userId);
	
	public Collection<User> findAllUsers();
	
	public User updateUser(User user);
	
	public void deleteById(Long userId);
}
