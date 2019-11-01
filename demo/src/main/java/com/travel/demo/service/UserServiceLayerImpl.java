package com.travel.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.demo.dao.UserDao;
import com.travel.demo.dto.User;

@Service
public class UserServiceLayerImpl implements UserServiceLayer {
	
    private UserDao userDao;
	
	public UserServiceLayerImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User saveUser(User user) {
		return this.userDao.addUser(user);
	}

	@Override
	public Collection<User> findAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User updateUser(User user) {
		return this.userDao.updateUser(user);
	}

	@Override
	public void deleteById(Long userId) {
		this.userDao.deleteUser(userId);
	}

	@Override
	public User getUserById(Long userId) {
		return this.userDao.getUserById(userId);
	}
	
}
