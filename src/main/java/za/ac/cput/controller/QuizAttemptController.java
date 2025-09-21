package za.ac.cput.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.ac.cput.domain.QuizAttempt;
import za.ac.cput.service.IQuizAttemptService;

@RestController
@RequestMapping("/quiz-attempts")
@CrossOrigin(origins = "*")
public class QuizAttemptController {
    private final IQuizAttemptService quizAttemptService;

    @Autowired
    public QuizAttemptController(IQuizAttemptService quizAttemptService) {
        this.quizAttemptService = quizAttemptService;
    }

    @PostMapping
    public ResponseEntity<QuizAttempt> submitAttempt(@RequestBody QuizAttempt attempt) {
        QuizAttempt saved = quizAttemptService.save(attempt);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<QuizAttempt>> getAllAttempts() {
        return ResponseEntity.ok(quizAttemptService.getAll());
    }
}
