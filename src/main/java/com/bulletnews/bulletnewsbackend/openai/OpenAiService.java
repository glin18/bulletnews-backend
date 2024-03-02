package com.bulletnews.bulletnewsbackend.openai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class OpenAiService {

    private final WebClient webClient;

    public OpenAiService(OpenAiConfig openAiConfig) {
        this.webClient = WebClient.builder()
                .baseUrl(openAiConfig.getBaseUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + openAiConfig.getApiKey())
                .build();
    }

    // TODO: 2024-02-27 Better exception handling
    public String generateShortArticle(String title, String content, String description) {
        try {
            String prompt = createPrompt(title, content, description);
            Map<String, Object> requestBody = createRequestBody(prompt);
            return webClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(OpenAiResponse.class)
                    .map(response -> response.getChoices().get(0).getMessage().getContent()).block();
        } catch (WebClientResponseException e) {
            log.error("Error Generating Short Article: {} Response Body: {}", e.getMessage(),
                    e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("Unexpected Error Generating Short Article: {}", e.getMessage());
        }
        return null;
    }

    private String createPrompt(String title, String content, String description) {
        return String.format("Write a concise, informative article suitable for a news website. " +
                        "The article should be based on the following information:" +
                        "\nTitle: %s\nContent Summary: %s\nDescription: %s\n" +
                        "Please ensure the article is factual, easy to read, and engaging for the audience.",
                title, content, description);
    }

    private Map<String, Object> createRequestBody(String prompt) {
        String SYSTEM_CONTENT = "Your task is to generate news articles that are accurate, engaging, " +
                "and professionally written. Focus on delivering clear information while maintaining a neutral tone. " +
                "Avoid any form of bias or opinion. Only give the content and not the title";
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("model", "gpt-4");
        requestMap.put("messages", Arrays.asList(
                Map.of("role", "system", "content", SYSTEM_CONTENT),
                Map.of("role", "user", "content", prompt)
        ));
        return requestMap;
    }

}
