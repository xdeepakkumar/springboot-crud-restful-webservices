package com.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.User;
import com.crud.exception.ResourceNotFoundException;
import com.crud.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	//get all user
	@GetMapping
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	
	
	//get user by id
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") Long userId) {
		return this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Id is not found with id: "+userId));
	}
	
	//create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	//update user
	@PutMapping("/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable("userId") long userId) {
		User existingUser =  this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Id is not found with id: "+userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
		
	}
	//delete user by id
	@DeleteMapping("/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable("userId") long userId){
		User existingUser =  this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Id is not found with id: "+userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
}
