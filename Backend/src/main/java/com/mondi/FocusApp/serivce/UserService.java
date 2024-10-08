package com.mondi.FocusApp.serivce;

import com.mondi.FocusApp.model.Role;
import com.mondi.FocusApp.model.User;
import com.mondi.FocusApp.repo.RoleRepository;
import com.mondi.FocusApp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public User saveUser(User user) {
        Role userRole = roleRepository.findByName("ADMIN");  // Fetch the "USER" role from the role repository
        if (userRole == null) {
            throw new RuntimeException("Role USER not found");  // Ensure that the role exists
        }
        user.setRoles(Collections.singletonList(userRole));  // Always assign the "USER" role
        user.setPassword(encoder.encode(user.getPassword()));  // Encode the password
        return userRepository.save(user);  // Save the user
    }


    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }



    // Method to delete a user
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
