package com.cyclemate.controller;

import com.cyclemate.model.Route;
import com.cyclemate.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteRepository routeRepository;

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return routeRepository.save(route);
    }

    @GetMapping("/{id}")
    public Optional<Route> getRoute(@PathVariable Long id) {
        return routeRepository.findById(id);
    }
}
