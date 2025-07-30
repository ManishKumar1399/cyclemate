package com.cyclemate.controller;

import com.cyclemate.model.RideHistory;
import com.cyclemate.model.Route;
import com.cyclemate.model.User;
import com.cyclemate.repository.RideHistoryRepository;
import com.cyclemate.repository.RouteRepository;
import com.cyclemate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideHistoryController {

    private final RideHistoryRepository rideHistoryRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;

    @GetMapping("/user/{userId}")
    public List<RideHistory> getRidesByUser(@PathVariable Long userId) {
        return rideHistoryRepository.findByUserId(userId);
    }

    @PostMapping
    public RideHistory createRide(@RequestBody RideHistory ride, @RequestParam Long userId, @RequestParam Long routeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new RuntimeException("Route not found"));
        ride.setUser(user);
        ride.setRoute(route);
        return rideHistoryRepository.save(ride);
    }
}
