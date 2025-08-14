package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;
import za.ac.cput.service.DeepSeekService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:5173")
public class DeepSeekController {

    private final DeepSeekService deepSeekService;

    public DeepSeekController(DeepSeekService deepSeekService) {
        this.deepSeekService = deepSeekService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String prompt) {
        return deepSeekService.getAIResponse(prompt);
    }
}

