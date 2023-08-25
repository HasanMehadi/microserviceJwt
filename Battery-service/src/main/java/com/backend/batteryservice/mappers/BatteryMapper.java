package com.backend.batteryservice.mappers;

import com.backend.batteryservice.dto.BatteryDto;
import com.backend.batteryservice.entities.Battery;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatteryMapper {
    ModelMapper modelMapper = new ModelMapper();

    public BatteryDto mapBattery(Battery battery) {
        return modelMapper.map(battery, BatteryDto.class);
    }

    public Battery mapBatteryDTO(BatteryDto batteryDTO) {
        return modelMapper.map(batteryDTO, Battery.class);
    }

    public List<BatteryDto> mapBatteryDTOList(List<Battery> batteryList) {
        List<BatteryDto> batteryDTOS = new ArrayList<>();
        for (Battery battery : batteryList) {
            batteryDTOS.add(modelMapper.map(battery, BatteryDto.class));
        }
        return batteryDTOS;
    }

    public List<Battery> mapBatteryList(List<BatteryDto> batteryDtos) {
        List<Battery> batteries = new ArrayList<>();
        for (BatteryDto batteryDto : batteryDtos) {
            batteries.add(modelMapper.map(batteryDto, Battery.class));
        }
        return batteries;
    }

}
