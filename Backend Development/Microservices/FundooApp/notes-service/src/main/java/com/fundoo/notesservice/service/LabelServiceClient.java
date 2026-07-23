package com.fundoo.notesservice.service;

import com.fundoo.notesservice.dto.response.ApiResponse;
import com.fundoo.notesservice.dto.response.LabelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LabelServiceClient {

    private final WebClient.Builder webClientBuilder;

    public LabelResponse getLabel(Long labelId) {

        ApiResponse<LabelResponse> response =
                webClientBuilder.build()
                        .get()
                        .uri("http://LABEL-SERVICE/api/labels/" + labelId)
                        .retrieve()
                        .bodyToMono(
                                new ParameterizedTypeReference<ApiResponse<LabelResponse>>() {
                                })
                        .block();

        return response.getData();
    }
}