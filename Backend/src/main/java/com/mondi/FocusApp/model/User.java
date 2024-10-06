package com.mondi.FocusApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")  // Or any other suitable name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String profileImage;

    private String password;
    @Column(unique = true)  // Enforce uniqueness on the username field
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Session> sessions;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private DailyProductivity dailyProductivity;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private WeeklyProductivity weeklyProductivity;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private MonthlyProductivity monthlyProductivity;
}
