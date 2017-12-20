package com.pearson.mysqlspringbootcrud.web;

import com.pearson.mysqlspringbootcrud.domain.User;
import com.pearson.mysqlspringbootcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // Get all users
    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Get one user
    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable(value = "id") Long id){
        return userRepository.findOne(id);
    }

    // Create new user
    @PostMapping(path = "/users")
    public User createNewUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    // Delete user
    @DeleteMapping(path = "/users/{id}")
    public User deleteUser(@PathVariable(value = "id") Long id){
        User user = userRepository.findOne(id);
        userRepository.delete(user);
        return user;
    }

    // Update user
    @PutMapping(path = "/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User user){
        User oldUser = userRepository.findOne(id);
        oldUser.setName(user.getName());

        User updatedUser = userRepository.save(oldUser);

        return updatedUser;
    }
}
