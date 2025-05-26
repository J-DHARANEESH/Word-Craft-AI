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
public class RephraseService implements AiProcessor {

    @Value("${local.rephrase.api.url:http://localhost:5000/rephrase}")
    private String rephraseApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String process(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = Map.of("text", prompt);

        try {
            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(rephraseApiUrl, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(response.getBody(), new TypeReference<>() {});
                List<String> paraphrases = (List<String>) map.get("rephrased");
                return String.join("\n", paraphrases);
            } else {
                return "Rephrase API call failed: " + response.getStatusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to get rephrase response: " + e.getMessage();
        }
    }
}
