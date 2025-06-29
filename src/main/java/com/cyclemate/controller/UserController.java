package com.cyclemate.controller;

import com.cyclemate.dto.LoginRequestDTO;
import com.cyclemate.dto.LoginResponseDTO;
import com.cyclemate.dto.UserRequestDTO;
import com.cyclemate.dto.UserResponseDTO;
import com.cyclemate.model.User;
import com.cyclemate.repository.UserRepository;
import com.cyclemate.security.JwtUtil;
import com.cyclemate.service.AuthService;
import com.cyclemate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthService authService;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO responseDTO = userService.registerUser(userRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
