package com.mondi.FocusApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // The name of the session type, e.g., 'Studying', 'Working Out', etc.
    private String color; // Color associated with the session type, e.g., '#FF5733'
}
