package com.cyclemate.service;

import com.cyclemate.dto.LoginRequestDTO;
import com.cyclemate.dto.LoginResponseDTO;
import com.cyclemate.dto.UserRequestDTO;
import com.cyclemate.dto.UserResponseDTO;
import com.cyclemate.model.User;
import com.cyclemate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO registerUser(UserRequestDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setAge(userDTO.getAge());
        user.setWeight(userDTO.getWeight());

        User saved = userRepository.save(user);

        return new UserResponseDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getAge(), saved.getWeight());
    }
    public LoginResponseDTO loginUser(LoginRequestDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponseDTO("Login successful for user: " + user.getName());
    }
}
