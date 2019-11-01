package com.travel.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.demo.dto.User;
import com.travel.demo.service.UserServiceLayer;

@RestController
public class UserController {

	@Autowired
	private UserServiceLayer service;

	public UserController(UserServiceLayer service) {
		this.service = service;
	}

	@GetMapping("/user")
	@CrossOrigin(origins = "http://localhost:4200")
	public Collection<User> getUsers() {
		System.out.println(service.findAllUsers());
		return service.findAllUsers();
	}
	
	@GetMapping("/user/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public User getUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}

	@PostMapping("/user")
	@CrossOrigin(origins = "http://localhost:4200")
	public User newUser(@RequestBody User newUser) {
		return service.saveUser(newUser);
	}

	@PutMapping("/user")
	@CrossOrigin(origins = "http://localhost:4200")
	User updateUser(@RequestBody User existingUser) {
		return service.updateUser(existingUser);
	}

	@DeleteMapping("/user/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	void deleteUser(@PathVariable Long id) {
		service.deleteById(id);
	}
}

//Create an API from scratch that takes user names, emails, one country they could
//attend and what dates they could attend that country. Find which dates for each country
//that the most people could attend in two consecutive days and return the dates as strings
//that they would be attending them. POST the countries with the dates as JSON properties
//to their server until you get a status of 200. The resulting JSON they're
//looking for does not match the example result provided.
