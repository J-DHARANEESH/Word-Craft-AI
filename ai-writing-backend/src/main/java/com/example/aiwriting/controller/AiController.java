package com.example.aiwriting.controller;

import com.example.aiwriting.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/{action}")
    public ResponseEntity<Map<String, String>> handleAction(
            @PathVariable String action,
            @RequestBody Map<String, String> payload) {

        String text = payload.get("text");
        if (text == null || text.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("result", "Input text is empty"));
        }

        String result = aiService.generateText(action, text);
        return ResponseEntity.ok(Map.of("result", result));
    }
}
