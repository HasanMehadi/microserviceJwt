package com.backend.batteryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryDto {

    private Long Id;
    private String name;
    private String postcode;
    private Integer capacity;
}
