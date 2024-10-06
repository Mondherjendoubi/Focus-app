package com.mondi.FocusApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyProductivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long totalSessions;
    private Long totalDuration; // in minutes
    private Double averageProductivityScore;
    private LocalDate weekStartDate;
    private LocalDate weekEndDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

