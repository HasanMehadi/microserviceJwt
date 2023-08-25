package com.backend.batteryservice.repository;

import com.backend.batteryservice.entities.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatteryRepository extends JpaRepository<Battery,Long> {

    Optional<Battery> findByPostcode(String postCode);

    List<Battery> findByCapacityLessThanEqual(Integer capacity);

    @Query("SELECT AVG(e.capacity) FROM Battery e")
    Integer getAverageCapacity();

    @Query("SELECT MAX(e.capacity) FROM Battery e")
    Integer getMaxCapacity();

    @Query("SELECT MIN(e.capacity) FROM Battery e")
    Integer getMinCapacity();

    @Query("SELECT e FROM Battery e WHERE e.capacity >= (SELECT MAX(e.capacity) FROM Battery e )")
    List<Battery> getMaximumCapacityBatteryList();

    @Query("SELECT e FROM Battery e WHERE e.capacity <= (SELECT MIN(e.capacity) FROM Battery e )")
    List<Battery> getMinimumCapacityBatteryList();
}
