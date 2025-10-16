package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.service.DeepSeekService;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:5173")
public class DeepSeekController {

    private final DeepSeekService deepSeekService;

    public DeepSeekController(DeepSeekService deepSeekService) {
        this.deepSeekService = deepSeekService;
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String prompt = payload.get("message");
        String reply = deepSeekService.getAIResponse(prompt);
        return Map.of("reply", reply);
    }

}
