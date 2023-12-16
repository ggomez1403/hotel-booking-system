package com.ggomezr.bookingsystem.application.service;

import com.ggomezr.bookingsystem.domain.dto.AuthenticationDto;
import com.ggomezr.bookingsystem.domain.dto.UserDto;
import com.ggomezr.bookingsystem.domain.entity.User;
import com.ggomezr.bookingsystem.domain.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String register(UserDto userDto){
        User user = User.builder()
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .email(userDto.email())
                .enable(true)
                .password(passwordEncoder.encode(userDto.password()))
                .role(userDto.role())
                .build();
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String authenticate(AuthenticationDto authenticationDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.email(),
                        authenticationDto.password()
                )
        );
        User user = userRepository.findUserByEmail(authenticationDto.email()).orElseThrow();
        return jwtService.generateToken(user);
    }
}
