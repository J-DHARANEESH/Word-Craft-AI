package com.example.aiwriting.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SummarizeService implements AiProcessor {

    @Value("${local.summarize.api.url:http://localhost:5000}")
    private String flaskApiBaseUrl;
   

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String process(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = Map.of("text", prompt);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            String endpoint = flaskApiBaseUrl + "/summarize";
            ResponseEntity<String> response = restTemplate.postForEntity(endpoint, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                return root.path("summary").asText("No summary returned.");
            } else {
                return "Flask summarize call failed: " + response.getStatusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to call Flask summarize service: " + e.getMessage();
        }
    }
}
