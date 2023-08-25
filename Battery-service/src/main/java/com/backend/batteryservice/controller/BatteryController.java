package com.backend.batteryservice.controller;

import com.backend.batteryservice.dto.BatteryDto;
import com.backend.batteryservice.service.BatteryService;
import com.backend.batteryservice.service.BucketLimitService;
import io.github.bucket4j.Bucket;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/battery")
public class BatteryController {

    @Autowired
    private BatteryService service;

    @Autowired
    private BucketLimitService limitService;


    private Bucket bucket = null;

    @PostConstruct
    private void postConstruct() {
        bucket = limitService.getBatteryServiceBucket();
    }

    @GetMapping("/home")
    public String greetingMessage() {


            return service.greeting();

    }

    @GetMapping("/status/{postcode}")
    public ResponseEntity<?> getBattery(@PathVariable String postcode) {

        if(bucket.tryConsume(1))
            return service.getBattery(postcode);
        else return new ResponseEntity<>("Too Many Request !! ", HttpStatus.TOO_MANY_REQUESTS);
    }

    @PostMapping("/save")
    public ResponseEntity<?> getBattery(@RequestBody List<BatteryDto> batteryDetails) {
        if(bucket.tryConsume(1)) {
            service.saveBatteryList(batteryDetails);
            return new ResponseEntity<>("updated successfully", HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>("Too Many Request !! ", HttpStatus.TOO_MANY_REQUESTS);
        }
    }

    @GetMapping("/get-aggregate-capacity")
    public ResponseEntity<?> getAggregateBatteryCapacity() {
        if(bucket.tryConsume(1))
            return service.getAggregateCapacity();
        else
            return new ResponseEntity<>("Too Many Request !! ", HttpStatus.TOO_MANY_REQUESTS);
    }

    @GetMapping("/max-capacity-battery-list")
    public ResponseEntity<?> getMaxCapacityBatteryList() {
        if(bucket.tryConsume(1))
            return service.getMaximumCapacityBatteryList();
        return new ResponseEntity<>("Too Many Request !! ", HttpStatus.TOO_MANY_REQUESTS);
    }

    @GetMapping("/min-capacity-battery-list")
    public ResponseEntity<?> getMinCapacityBatteryList() {
        return service.getMinimumCapacityBatteryList();
    }

    @GetMapping("/status/capacity/{capacity}")
    public ResponseEntity<?> getThresholdBattery(@PathVariable Integer capacity) {

        if(bucket.tryConsume(1))
            return service.getThresholdBattery(capacity);
        else return new ResponseEntity<>("Too Many Request !! ", HttpStatus.TOO_MANY_REQUESTS);
    }
}
