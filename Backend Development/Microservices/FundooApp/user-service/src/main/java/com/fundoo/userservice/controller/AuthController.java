package com.fundoo.userservice.controller;


import com.fundoo.userservice.dto.request.LoginRequest;
import com.fundoo.userservice.dto.request.RegisterRequest;
import com.fundoo.userservice.dto.response.ApiResponse;
import com.fundoo.userservice.dto.response.LoginResponse;
import com.fundoo.userservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fundoo.userservice.dto.response.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/email/{email}")
    public ApiResponse<UserResponse> getUserByEmail(
            @PathVariable String email) {

        return new ApiResponse<>(
                "Success",
                authService.getUserByEmail(email)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(
            @PathVariable Long id) {

        return new ApiResponse<>(
                "Success",
                authService.getUserById(id));
    }
}
