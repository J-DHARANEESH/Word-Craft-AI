package com.example.aiwriting.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpandService implements AiProcessor {

    @Value("${local.expand.api.url:http://localhost:5000/expand}")
    private String expandApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String process(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = Map.of("text", prompt);

        try {
            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(expandApiUrl, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(response.getBody(), new TypeReference<>() {});
                List<String> expansions = (List<String>) map.get("expanded");
                return String.join("\n", expansions);
            } else {
                return "Expand API call failed: " + response.getStatusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to get expand response: " + e.getMessage();
        }
    }
}
