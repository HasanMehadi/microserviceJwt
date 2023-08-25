package com.backend.batteryservice.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Battery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private String postcode;
    private Integer capacity;
}
