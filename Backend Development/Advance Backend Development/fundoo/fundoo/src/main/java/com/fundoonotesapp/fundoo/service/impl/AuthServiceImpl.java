package com.fundoonotesapp.fundoo.service.impl;


import com.fundoonotesapp.fundoo.dto.request.LoginRequest;
import com.fundoonotesapp.fundoo.dto.request.RegisterRequest;
import com.fundoonotesapp.fundoo.dto.response.LoginResponse;
import com.fundoonotesapp.fundoo.entity.User;
import com.fundoonotesapp.fundoo.exception.UserAlreadyExistsException;
import com.fundoonotesapp.fundoo.exception.UserNotFoundException;
import com.fundoonotesapp.fundoo.repository.UserRepository;
import com.fundoonotesapp.fundoo.security.JwtService;
import com.fundoonotesapp.fundoo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final StringRedisTemplate redisTemplate;

    @Override
    public String register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())) {

            throw new UserAlreadyExistsException(
                    "Email already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .verified(true)
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid Credentials");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail());

        redisTemplate.opsForValue()
                .set(
                        "jwt:" + user.getEmail(),
                        token
                );

        return LoginResponse.builder()
                .email(user.getEmail())
                .token(token)
                .build();
    }
}