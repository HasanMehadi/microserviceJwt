package com.example.powerplant.client;
import com.example.powerplant.dto.BatteryDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BatteryServiceClient {

    @Autowired
    private RestTemplate template;

    public BatteryDto fetchBatteryStatus(String batteryId) {
        return template.getForObject("http://BATTERY-SERVICE/battery/status" + batteryId, BatteryDto.class);
    }
}
