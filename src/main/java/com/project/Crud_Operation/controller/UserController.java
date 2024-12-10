package com.project.Crud_Operation.controller;

import com.project.Crud_Operation.exception.UserNotFoundException;
import com.project.Crud_Operation.model.User;
import com.project.Crud_Operation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("https://vigneshcrudapplication.vercel.app")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    void addUser( @RequestBody User newUser) {
        userRepository.save(newUser);
    }

    @GetMapping("/user")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }
    @DeleteMapping("user/{id}")
    public Map<String, String> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);


        Map<String, String> response = new HashMap<>();
        response.put("message", "User with id " + id + " is deleted successfully");
        return response;
    }

}
