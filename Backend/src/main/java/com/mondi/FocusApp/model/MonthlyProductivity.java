package com.mondi.FocusApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyProductivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long totalSessions;
    private Long totalDuration; // in minutes
    private Double averageProductivityScore;
    private int month; // Month in numerical form (1 = January, etc.)
    private int year;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

