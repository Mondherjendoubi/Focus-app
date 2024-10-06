package com.mondi.FocusApp.controller;

import com.mondi.FocusApp.model.SessionType;
import com.mondi.FocusApp.repo.SessionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/session-types")
public class SessionTypeController {

    @Autowired
    private SessionTypeRepository sessionTypeRepository;

    // Get all session types
    @GetMapping
    public List<SessionType> getAllSessionTypes() {
        return sessionTypeRepository.findAll();
    }

    // Add new session type
    @PostMapping
    public ResponseEntity<SessionType> createSessionType(@RequestBody SessionType sessionType) {
        SessionType savedSessionType = sessionTypeRepository.save(sessionType);
        return new ResponseEntity<>(savedSessionType, HttpStatus.CREATED);
    }

    // Delete a session type by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSessionType(@PathVariable Long id) {
        Optional<SessionType> sessionType = sessionTypeRepository.findById(id);
        if (sessionType.isPresent()) {
            sessionTypeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
