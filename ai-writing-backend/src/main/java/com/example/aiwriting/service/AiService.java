//package com.example.aiwriting.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.type.TypeReference;
//
//import java.util.*;
//
//@Service
//@RequiredArgsConstructor
//public class AiService {
//
//    @Value("${huggingface.api.token}")
//    private String hfApiToken;
//
//    @Value("${local.rephrase.api.url:http://localhost:5000/rephrase}")
//    private String localRephraseApiUrl;
//
//    @Value("${local.expand.api.url:http://localhost:5000/expand}")
//    private String localExpandApiUrl;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    private static final Map<String, String> MODEL_MAP = Map.of(
//            "summarize", "facebook/bart-large-cnn"
//            // "expand" and "rephrase" handled locally
//    );
//
//    public String generateText(String action, String prompt) {
//        if ("rephrase".equalsIgnoreCase(action)) {
//            // Call local Flask rephrase API
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            Map<String, String> body = Map.of("text", prompt);
//            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
//
//            try {
//                ResponseEntity<String> response = restTemplate.postForEntity(localRephraseApiUrl, request, String.class);
//                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
//                    ObjectMapper mapper = new ObjectMapper();
//                    Map<String, Object> responseMap = mapper.readValue(response.getBody(), new TypeReference<>() {});
//                    @SuppressWarnings("unchecked")
//                    List<String> paraphrases = (List<String>) responseMap.get("rephrased");
//                    if (paraphrases != null) {
//                        return String.join("\n", paraphrases);
//                    } else {
//                        return "No paraphrases returned from local rephrase service.";
//                    }
//                } else {
//                    return "Local rephrase API call failed: " + response.getStatusCode();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return "Failed to get rephrase response: " + e.getMessage();
//            }
//        } else if ("expand".equalsIgnoreCase(action)) {
//            // Call local Flask expand API
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            Map<String, String> body = Map.of("text", prompt);
//            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
//
//            try {
//                ResponseEntity<String> response = restTemplate.postForEntity(localExpandApiUrl, request, String.class);
//                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
//                    ObjectMapper mapper = new ObjectMapper();
//                    Map<String, Object> responseMap = mapper.readValue(response.getBody(), new TypeReference<>() {});
//                    @SuppressWarnings("unchecked")
//                    List<String> expansions = (List<String>) responseMap.get("expanded");
//                    if (expansions != null) {
//                        return String.join("\n", expansions);
//                    } else {
//                        return "No expansions returned from local expand service.";
//                    }
//                } else {
//                    return "Local expand API call failed: " + response.getStatusCode();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return "Failed to get expand response: " + e.getMessage();
//            }
//        }
//
//        // For other actions like "summarize", call Hugging Face API as before
//        String modelId = MODEL_MAP.get(action.toLowerCase());
//        if (modelId == null) {
//            return "Unsupported action: " + action;
//        }
//
//        String apiUrl = "https://api-inference.huggingface.co/models/" + modelId;
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        headers.setBearerAuth(hfApiToken);
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("inputs", prompt);
//
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
//
//        try {
//            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
//            String responseBody = response.getBody();
//
//            if (response.getStatusCode() == HttpStatus.OK && responseBody != null) {
//                ObjectMapper mapper = new ObjectMapper();
//                List<Map<String, Object>> results = mapper.readValue(responseBody, new TypeReference<>() {});
//                if (!results.isEmpty()) {
//                    Map<String, Object> first = results.get(0);
//                    if (action.equalsIgnoreCase("summarize")) {
//                        return Objects.toString(first.get("summary_text"), "No summary returned.");
//                    }
//                }
//            } else {
//                return "API call failed: " + response.getStatusCode();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Failed to get AI response: " + e.getMessage();
//        }
//
//        return "No response from AI model.";
//    }
//}
package com.example.aiwriting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

@Service
public class AiService {

    private final Map<String, AiProcessor> processorMap;

    // Constructor injection: Spring will provide the implementations
    public AiService(
            RephraseService rephraseService,
            ExpandService expandService,
            SummarizeService summarizeService
    ) {
        this.processorMap = Map.of(
                "rephrase", rephraseService,
                "expand", expandService,
                "summarize", summarizeService
        );
    }

    public String generateText(String action, String prompt) {
        AiProcessor processor = processorMap.get(action.toLowerCase(Locale.ROOT));
        if (processor == null) {
            return "Unsupported action: " + action;
        }
        return processor.process(prompt);
    }
}
