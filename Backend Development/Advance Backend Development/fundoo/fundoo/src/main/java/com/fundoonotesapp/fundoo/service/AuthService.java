package com.fundoonotesapp.fundoo.service;

import com.fundoonotesapp.fundoo.dto.request.LoginRequest;
import com.fundoonotesapp.fundoo.dto.request.RegisterRequest;
import com.fundoonotesapp.fundoo.dto.response.LoginResponse;

public interface AuthService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
