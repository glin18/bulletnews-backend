package com.bulletnews.bulletnewsbackend.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OpenAiService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public OpenAiService(OpenAiConfig openAiConfig, ObjectMapper objectMapper) {
        this.webClient = WebClient.builder()
                .baseUrl(openAiConfig.getBaseUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + openAiConfig.getApiKey())
                .build();
        this.objectMapper = objectMapper;
    }

    public String generateShortArticle(String prompt) {
        try {
            String requestBody = createRequestBody(prompt);
            return webClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(OpenAiResponse.class)
                    .map(response -> response.getChoices().get(0).getMessage().getContent()).block();
        } catch (Exception e) {
            log.error("Error Generating Short Article", e);
            return null;
        }
    }

    private String createRequestBody(String prompt) throws JsonProcessingException {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("model", "gpt-4");
        requestMap.put("messages", Arrays.asList(
                Map.of("role", "system", "content", "You are creating concise news articles based on " +
                        "content provided to you"),
                Map.of("role", "user", "content", prompt)
        ));
        return objectMapper.writeValueAsString(requestMap);
    }

}
