package com.mondi.FocusApp.repo;

import com.mondi.FocusApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);


    // Method to find all users with the same username (for detecting duplicates)
    List<User> findAllByUsername(String username);


}