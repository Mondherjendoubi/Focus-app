package com.mondi.FocusApp.controller;


import com.mondi.FocusApp.model.User;
import com.mondi.FocusApp.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.mondi.FocusApp.serivce.JwtService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    private JwtService jwtService;


    @PostMapping("register")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        // Check if username already exists
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(409).body("Username is already taken");
        }

        // Add new user
        User newUser = userService.saveUser(user);
        return ResponseEntity.status(201).body("User registered successfully");
    }


    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getUsername());
                return ResponseEntity.ok(token);  // Return token with 200 OK status
            } else {
                return ResponseEntity.status(401).body("Login Failed");  // Return 401 Unauthorized with message
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Login Failed: Invalid credentials");  // Handle exception, return 401 Unauthorized
        }
    }


    // Endpoint to get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint to get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Endpoint to delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



}

