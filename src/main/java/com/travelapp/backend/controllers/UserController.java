package com.travelapp.backend.controllers;

import java.util.List;

import com.travelapp.backend.models.User;
import com.travelapp.backend.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "api/v1/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable("userId") Integer id){
        return this.userService.getUserById(id);
    }

  
    @PostMapping
	public User addNewUser(@RequestBody User user){
		return this.userService.addNewUser(user);
	}

    @PutMapping(path = "{userId}")
	public User updateUserById(
        @PathVariable("userId") Integer userId,
        @RequestBody User newUser
    ){
		return this.userService.updateUser(userId, newUser);
	}

    @DeleteMapping(path = "{userId}")
	public void deleteUserById(
        @PathVariable("userId") Integer id
    ){
		this.userService.deleteUserById(id);
	}

}

