package com.cyclemate.controller;

import com.cyclemate.model.Route;
import com.cyclemate.model.User;
import com.cyclemate.repository.RouteRepository;
import com.cyclemate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

//    @GetMapping
//    public List<Route> getAllRoutes() {
//        return routeRepository.findAll();
//    }
    @GetMapping
    public ResponseEntity<List<Route>> getUserRoutes(Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(routeRepository.findByCreatedBy(user));
    }

//    @PostMapping
//    public Route createRoute(@RequestBody Route route) {
//        return routeRepository.save(route);
//    }
    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route, @RequestParam Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        route.setCreatedBy(user);
        return ResponseEntity.ok(routeRepository.save(route));
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setAge(updatedUser.getAge());
            user.setWeight(updatedUser.getWeight());
            return ResponseEntity.ok(userRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }




    @GetMapping("/{id}")
    public Optional<Route> getRoute(@PathVariable Long id) {
        return routeRepository.findById(id);
    }
}
