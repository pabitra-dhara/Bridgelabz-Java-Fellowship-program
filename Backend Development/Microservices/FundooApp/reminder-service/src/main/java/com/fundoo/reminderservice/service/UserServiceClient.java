package com.fundoo.reminderservice.service;

import com.fundoo.reminderservice.dto.UserResponse;
import com.fundoo.reminderservice.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserServiceClient {

    private final WebClient.Builder webClientBuilder;

    public UserResponse getUserByEmail(String email) {

        ApiResponse<UserResponse> response =
                webClientBuilder.build()
                        .get()
                        .uri("http://USER-SERVICE/api/auth/email/" + email)
                        .retrieve()
                        .bodyToMono(
                                new ParameterizedTypeReference<ApiResponse<UserResponse>>() {})
                        .block();

        return response.getData();
    }

    public UserResponse getUserById(Long id) {

        ApiResponse<UserResponse> response =
                webClientBuilder.build()
                        .get()
                        .uri("http://USER-SERVICE/api/auth/" + id)
                        .retrieve()
                        .bodyToMono(
                                new ParameterizedTypeReference<ApiResponse<UserResponse>>() {})
                        .block();

        return response.getData();
    }
}