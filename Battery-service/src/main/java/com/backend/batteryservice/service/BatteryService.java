package com.backend.batteryservice.service;

import com.backend.batteryservice.dto.BatteryDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BatteryService {
    String greeting();
    ResponseEntity<?> getBattery(String postcode);
    void saveBatteryList(List<BatteryDto> batteryDtos);

    ResponseEntity<?> getAggregateCapacity();

    ResponseEntity<?> getMaximumCapacityBatteryList();

    ResponseEntity<?> getMinimumCapacityBatteryList();

    ResponseEntity<?> getThresholdBattery(Integer capacity);
}
