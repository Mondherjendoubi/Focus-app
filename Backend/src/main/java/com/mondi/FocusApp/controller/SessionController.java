package com.mondi.FocusApp.controller;

import com.mondi.FocusApp.model.Session;
import com.mondi.FocusApp.serivce.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;


    @GetMapping // New method to retrieve all sessions
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/user/{userId}")
    public List<Session> getSessionsByUser(@PathVariable Long userId) {
        return sessionService.getSessionsByUser(userId);
    }

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody Session session,
                                                 @RequestParam Long userId) {
        Session savedSession = sessionService.createSession(session, userId);
        return new ResponseEntity<>(savedSession, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Fetch all sessions for a specific user on a specific date
    @GetMapping("/user/{userId}/date/{date}")
    public List<Session> getSessionsByUserAndDate(@PathVariable Long userId, @PathVariable String date) {
        LocalDate sessionDate = LocalDate.parse(date); // Convert string to LocalDate
        return sessionService.getSessionsByUserAndDate(userId, sessionDate);
    }

}

