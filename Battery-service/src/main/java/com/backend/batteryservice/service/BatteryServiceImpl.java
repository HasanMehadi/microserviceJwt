package com.backend.batteryservice.service;


import com.backend.batteryservice.dto.BatteryDto;
import com.backend.batteryservice.entities.Battery;
import com.backend.batteryservice.mappers.BatteryMapper;
import com.backend.batteryservice.repository.BatteryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class BatteryServiceImpl implements BatteryService{

    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private BatteryMapper batteryMapper;
    @Override
    public String greeting() {
        return "Welcome to Battery service";
    }

    @Override
    public ResponseEntity<?> getBattery(String postcode) {
        Optional<Battery> battery = batteryRepository.findByPostcode(postcode);

        try {
            return new ResponseEntity<>(Base64.encodeBase64(batteryMapper.mapBattery(battery.get()).toString().getBytes()), HttpStatus.OK);
        }catch (Exception ex){

        }
        return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public void saveBatteryList(List<BatteryDto> batteryDtos) {
        log.info("saving list of data");
        try{
            List<Battery> batteries = batteryMapper.mapBatteryList(batteryDtos);
            for (Battery battery: batteries) {
                if(batteryRepository.findByPostcode(battery.getPostcode()).isEmpty()){
                    batteryRepository.save(battery);
                }
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAggregateCapacity() {

        try {
            Map<String,Object> aggregateCapacity= new HashMap<>();
            aggregateCapacity.put("Avg",batteryRepository.getAverageCapacity());
            aggregateCapacity.put("Max",batteryRepository.getMaxCapacity());
            aggregateCapacity.put("Min",batteryRepository.getMinCapacity());
            return new ResponseEntity<>(Base64.encodeBase64(aggregateCapacity.toString().getBytes()), HttpStatus.OK);
        }catch (Exception ex){

        }
        return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getMaximumCapacityBatteryList() {


        try {
            return new ResponseEntity<>(batteryRepository.getMaximumCapacityBatteryList(), HttpStatus.OK);
        }catch (Exception ex){

        }
        return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getMinimumCapacityBatteryList() {
        try {
            return new ResponseEntity<>(batteryRepository.getMinimumCapacityBatteryList(), HttpStatus.OK);
        }catch (Exception ex){

        }
        return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getThresholdBattery(Integer capacity) {
        List<Battery> batteryList = batteryRepository.findByCapacityLessThanEqual(capacity);

        try {
            if(!batteryList.isEmpty())
                return new ResponseEntity<>(Base64.encodeBase64(batteryMapper.mapBatteryDTOList(batteryList).toString().getBytes()), HttpStatus.OK);
        }catch (Exception ex){

        }
        return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
    }
}
