package com.mondi.FocusApp.serivce;

import com.mondi.FocusApp.model.User;
import com.mondi.FocusApp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return userRepository.save(user) ;
    }




    // Method to fetch all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to fetch a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }



    // Method to delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
