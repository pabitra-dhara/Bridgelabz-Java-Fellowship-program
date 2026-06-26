package com.fundoo.userservice.service;


import com.fundoo.userservice.dto.request.LoginRequest;
import com.fundoo.userservice.dto.request.RegisterRequest;
import com.fundoo.userservice.dto.response.LoginResponse;

public interface AuthService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
