package za.ac.cput.service;

import za.ac.cput.DTO.DeepSeekRequest;
import za.ac.cput.DTO.DeepSeekResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DeepSeekService {

    private final WebClient webClient;

    @Value("${deepseek.api.url}")
    private String apiUrl; // e.g. https://openrouter.ai/api/v1/chat/completions

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

        try {
            DeepSeekResponse response = webClient.post()
                    .uri(apiUrl) // now uses full OpenRouter endpoint directly
                    .headers(h -> {
                        h.setBearerAuth(apiKey);
                        h.add("HTTP-Referer", "http://localhost:5173"); // optional but OpenRouter recommends setting this
                        h.add("X-Title", "Prodemy Chatbot"); // optional: helps identify your app in OpenRouter dashboard
                    })
                    .header("Content-Type", "application/json")
                    .bodyValue(request)
                    .retrieve()
                    .onStatus(status -> !status.is2xxSuccessful(),
                            clientResponse -> clientResponse.bodyToMono(String.class)
                                    .flatMap(body -> Mono.error(new RuntimeException(
                                            "DeepSeek error: " + clientResponse.statusCode() + " - " + body))))
                    .bodyToMono(DeepSeekResponse.class)
                    .block();

            if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                return response.getChoices().get(0).getMessage().getContent();
            } else {
                return "No response from DeepSeek (OpenRouter).";
            }

        } catch (WebClientResponseException wcEx) {
            return "DeepSeek HTTP error: " + wcEx.getRawStatusCode() + " - " + wcEx.getResponseBodyAsString();
        } catch (Exception ex) {
            return "Error calling DeepSeek (OpenRouter): " + ex.getMessage();
        }
    }
}
