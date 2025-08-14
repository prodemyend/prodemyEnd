package za.ac.cput.service;

import za.ac.cput.DTO.DeepSeekRequest;
import za.ac.cput.DTO.DeepSeekResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class DeepSeekService {

    private final WebClient webClient;

    @Value("${deepseek.api.url}")
    private String apiUrl;

    @Value("${deepseek.api.key}")
    private String apiKey;

    @Value("${deepseek.model}")
    private String model;

    public DeepSeekService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public String getAIResponse(String prompt) {
        DeepSeekRequest request = new DeepSeekRequest(
                model,
                List.of(
                        new DeepSeekRequest.Message("system", "You are a helpful assistant."),
                        new DeepSeekRequest.Message("user", prompt)
                )
        );

        DeepSeekResponse response = webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(DeepSeekResponse.class)
                .block();

        if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
            return response.getChoices().get(0).getMessage().getContent();
        } else {
            return "No response from DeepSeek.";
        }
    }
}
