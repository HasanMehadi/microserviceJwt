package com.example.powerplant.service;

import com.example.powerplant.dto.BatteryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
public interface PowerPlantService {


    String greeting();

    BatteryDto checkBatteryStatus(String orderId);
}
