package com.cyclemate.controller;

import com.cyclemate.model.User;
import com.cyclemate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{userId}/disable")
    public void disableUser(@PathVariable Long userId) {
        userService.disableUser(userId);
    }

    @PutMapping("/users/{userId}/enable")
    public void enableUser(@PathVariable Long userId) {
        userService.enableUser(userId);
    }
}
