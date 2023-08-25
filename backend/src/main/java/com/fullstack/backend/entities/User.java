package com.fullstack.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private Boolean isActive;

    private Boolean isExpired;

    @ManyToOne()
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = true)
    private Profile profile;

}
