package com.fullstack.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;


}
