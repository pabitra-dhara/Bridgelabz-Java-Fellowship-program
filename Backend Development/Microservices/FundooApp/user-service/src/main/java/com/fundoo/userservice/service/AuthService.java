package com.fundoo.userservice.service;


import com.fundoo.userservice.dto.request.LoginRequest;
import com.fundoo.userservice.dto.request.RegisterRequest;
import com.fundoo.userservice.dto.response.LoginResponse;
import com.fundoo.userservice.dto.response.UserResponse;

public interface AuthService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    UserResponse getUserByEmail(String email);

    UserResponse getUserById(Long id);
}