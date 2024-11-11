package com.example.java_vector_search.Services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service; // Add this import
import org.springframework.web.reactive.function.client.WebClient;

import com.example.java_vector_search.EmbeddingResponse;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service // Add this annotation
public class OpenAIService {

    private static final String OPENAI_API_URL = "https://api.openai.com";

    @Value("${openai.api.key}")
    private String OPENAI_API_KEY;
    
    private WebClient webClient;

    @PostConstruct
    void init() {
        this.webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector())
            .baseUrl(OPENAI_API_URL)
            .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("Authorization", "Bearer " + OPENAI_API_KEY) // Added space after "Bearer"
            .build();
    }

    public Mono<List<Double>> createEmbeddings(String text) {
        Map<String, Object> body = Map.of(
            "model", "text-embedding-ada-002",
            "input", text
        );
        System.out.println("Calling Post Request");
        System.out.println(body);
        return webClient.post()
            .uri("/v1/embeddings") 
            .bodyValue(body)
            .retrieve()
            .bodyToMono(EmbeddingResponse.class)
            .map(EmbeddingResponse::getEmbedding) 
            .onErrorResume(e -> {
                System.err.println("Error while creating embeddings: " + e.getMessage());
                return Mono.empty(); 
            });
    }
}
