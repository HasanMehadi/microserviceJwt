package com.fullstack.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne()
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

}
