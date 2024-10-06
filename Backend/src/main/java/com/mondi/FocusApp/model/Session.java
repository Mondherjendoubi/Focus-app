package com.mondi.FocusApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long duration; // in minutes

    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // This will prevent user data from being serialized in the session
    private User user;


    @ManyToOne
    @JoinColumn(name = "session_type_id")
    private SessionType sessionType; // Reference to the SessionType entity


    @PrePersist
    public void calculateDuration() {
        if (startTime != null && endTime != null) {
            this.duration = java.time.Duration.between(startTime, endTime).toMinutes();
        }
    }
}

