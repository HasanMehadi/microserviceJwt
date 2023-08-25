package com.example.powerplant.controller;

import com.example.powerplant.dto.BatteryDto;
import com.example.powerplant.service.PowerPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/power-plant")
public class PowerPlantController {

    @Autowired
    private PowerPlantService service;

    @GetMapping("/home")
    public String greetingMessage() {
        return service.greeting();
    }

    @GetMapping("/{orderId}")
    public BatteryDto checkOrderStatus(@PathVariable String batteryId) {
        return service.checkBatteryStatus(batteryId);
    }
}
