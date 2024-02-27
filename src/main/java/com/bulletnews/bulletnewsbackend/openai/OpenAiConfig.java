package com.bulletnews.bulletnewsbackend.openai;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class OpenAiConfig {

    @Value("${OPENAI_BASE_URL}")
    private String baseUrl;

    @Value("${OPENAI_API_KEY}")
    private String apiKey;

}
