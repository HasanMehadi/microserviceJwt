package com.backend.batteryservice.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class BucketLimitService {

    @Value("${bucket.request-token}")
    private Integer requestToken;

    @Value("${bucket.capacity}")
    private Integer capacity;

    public Bucket getBatteryServiceBucket(){
        return Bucket4j.builder().addLimit(getClientBandWidth()).build();
    }

    private Bandwidth getClientBandWidth() {

        return Bandwidth.classic(capacity, Refill.of(requestToken, Duration.ofMinutes(1)));
    }
}
