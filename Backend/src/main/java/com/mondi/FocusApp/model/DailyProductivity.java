package com.mondi.FocusApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyProductivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long totalSessions;
    private Long totalDuration; // in minutes
    private Double productivityScore;
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // Prevents infinite loop during serialization
    private User user;
}
