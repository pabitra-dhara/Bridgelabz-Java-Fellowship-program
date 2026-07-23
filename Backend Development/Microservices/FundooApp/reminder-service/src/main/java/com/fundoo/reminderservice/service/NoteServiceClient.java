package com.fundoo.reminderservice.service;

import com.fundoo.reminderservice.dto.response.NoteResponse;
import com.fundoo.reminderservice.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class NoteServiceClient {

    private final WebClient.Builder webClientBuilder;

    public NoteResponse getNote(Long noteId) {

        ApiResponse<NoteResponse> response =
                webClientBuilder.build()
                        .get()
                        .uri("http://NOTES-SERVICE/api/notes/" + noteId)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<ApiResponse<NoteResponse>>() {})
                        .block();

        return response.getData();
    }
}