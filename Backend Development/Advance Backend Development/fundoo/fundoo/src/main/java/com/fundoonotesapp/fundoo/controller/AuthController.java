package com.fundoonotesapp.fundoo.controller;

import com.fundoonotesapp.fundoo.dto.request.LoginRequest;
import com.fundoonotesapp.fundoo.dto.request.RegisterRequest;
import com.fundoonotesapp.fundoo.dto.response.ApiResponse;
import com.fundoonotesapp.fundoo.dto.response.LoginResponse;
import com.fundoonotesapp.fundoo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<String> register(
            @Valid @RequestBody RegisterRequest request) {

        return new ApiResponse<>(
                "Success",
                authService.register(request));
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return new ApiResponse<>(
                "Success",
                authService.login(request));
    }
}
