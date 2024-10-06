package com.mondi.FocusApp.serivce;

import com.mondi.FocusApp.model.Session;
import com.mondi.FocusApp.model.User;
import com.mondi.FocusApp.repo.SessionRepository;
import com.mondi.FocusApp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Session> getSessionsByUser(Long userId) {
        return sessionRepository.findByUserId(userId);
    }

    public Session createSession(Session session, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        session.setUser(user); // Set the user to the session
        return sessionRepository.save(session); // Save the session
    }

    public List<Session> getAllSessions() { // New method to get all sessions
        return sessionRepository.findAll();
    }


    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }

    public List<Session> getSessionsByUserAndDate(Long userId, LocalDate date) {
        return sessionRepository.findSessionsByUserIdAndDate(userId, date);
    }
}
