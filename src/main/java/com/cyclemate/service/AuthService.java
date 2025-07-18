package com.cyclemate.service;

import com.cyclemate.dto.LoginRequestDTO;
import com.cyclemate.dto.LoginResponseDTO;
import com.cyclemate.model.User;
import com.cyclemate.repository.UserRepository;
import com.cyclemate.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService; // for loading user
    @Autowired
    private UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO loginDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );

        // If auth succeeds, generate token
        String username = loginDTO.getEmail();
        // ✅ Fetch the full User entity
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 🔁 Generate tokens using full User object
        String token = jwtUtil.generateToken(user);

//        String token = jwtUtil.generateToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        return new LoginResponseDTO(token, refreshToken);
    }
}
