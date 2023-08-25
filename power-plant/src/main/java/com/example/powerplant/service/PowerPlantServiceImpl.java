package com.example.powerplant.service;

import com.example.powerplant.client.BatteryServiceClient;
import com.example.powerplant.dto.BatteryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerPlantServiceImpl implements PowerPlantService{

    @Autowired
    private BatteryServiceClient batteryServiceClient;

    @Override
    public String greeting() {
        return "Welcome to Power Plant App Service";
    }

    @Override
    public BatteryDto checkBatteryStatus(String orderId) {
        return batteryServiceClient.fetchBatteryStatus(orderId);
    }
}
