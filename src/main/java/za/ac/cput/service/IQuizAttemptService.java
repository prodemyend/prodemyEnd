package za.ac.cput.service;

import java.util.List;
import java.util.Optional;

import za.ac.cput.domain.QuizAttempt;

public interface IQuizAttemptService {
    QuizAttempt save(QuizAttempt attempt);
    List<QuizAttempt> getAll();
    Optional<QuizAttempt> findById(Long id);
}
