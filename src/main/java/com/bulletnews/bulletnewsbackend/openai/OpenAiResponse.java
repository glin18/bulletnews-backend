package com.bulletnews.bulletnewsbackend.openai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class OpenAiResponse {
    @JsonProperty("choices")
    private List<ChoicesDTO> choices;
    @JsonProperty("created")
    private Integer created;
    @JsonProperty("id")
    private String id;
    @JsonProperty("model")
    private String model;
    @JsonProperty("object")
    private String object;
    @JsonProperty("usage")
    private UsageDTO usage;

    @NoArgsConstructor
    @Data
    public static class UsageDTO {
        @JsonProperty("completion_tokens")
        private Integer completionTokens;
        @JsonProperty("prompt_tokens")
        private Integer promptTokens;
        @JsonProperty("total_tokens")
        private Integer totalTokens;
    }

    @NoArgsConstructor
    @Data
    public static class ChoicesDTO {
        @JsonProperty("finish_reason")
        private String finishReason;
        @JsonProperty("index")
        private Integer index;
        @JsonProperty("message")
        private MessageDTO message;
        @JsonProperty("logprobs")
        private Object logprobs;

        @NoArgsConstructor
        @Data
        public static class MessageDTO {
            @JsonProperty("content")
            private String content;
            @JsonProperty("role")
            private String role;
        }
    }
}
