package com.cyclemate.controller;

import com.cyclemate.model.RideHistory;
import com.cyclemate.repository.RideHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideHistoryController {

    private final RideHistoryRepository rideHistoryRepository;

    @GetMapping("/user/{userId}")
    public List<RideHistory> getRidesByUser(@PathVariable Long userId) {
        return rideHistoryRepository.findByUserId(userId);
    }

    @PostMapping
    public RideHistory createRide(@RequestBody RideHistory ride) {
        return rideHistoryRepository.save(ride);
    }
}
