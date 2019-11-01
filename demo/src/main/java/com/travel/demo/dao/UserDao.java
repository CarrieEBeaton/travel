package com.travel.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.travel.demo.dto.User;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface UserDao {

	public User addUser(User user);

	public void deleteUser(Long userId);

	public User updateUser(User user);

	public User getUserById(Long userId);

	public List<User> getAllUsers();

}
