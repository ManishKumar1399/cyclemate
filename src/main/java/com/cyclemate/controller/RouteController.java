package com.cyclemate.controller;

import com.cyclemate.exception.RouteNotFoundException;
import com.cyclemate.model.Route;
import com.cyclemate.model.User;
import com.cyclemate.repository.RouteRepository;
import com.cyclemate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Route>> getUserRoutes(Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return ResponseEntity.ok(routeRepository.findByCreatedBy(user));
    }

    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route, @RequestParam Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        route.setCreatedBy(user);
        return ResponseEntity.ok(routeRepository.save(route));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable Long id) {
        return routeRepository.findById(id).map(route -> {
            routeRepository.delete(route);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RouteNotFoundException("Route not found with id: " + id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRoute(@PathVariable Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route not found with id: " + id));
        return ResponseEntity.ok(route);
    }
}
