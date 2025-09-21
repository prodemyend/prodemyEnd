package za.ac.cput.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.ac.cput.domain.QuizAttempt;
import za.ac.cput.repository.QuizAttemptRepository;

@Service
public class QuizAttemptService implements IQuizAttemptService {
    private final QuizAttemptRepository repository;

    @Autowired
    public QuizAttemptService(QuizAttemptRepository repository) {
        this.repository = repository;
    }

    @Override
    public QuizAttempt save(QuizAttempt attempt) {
        return repository.save(attempt);
    }

    @Override
    public List<QuizAttempt> getAll() {
        return repository.findAll();
    }

    @Override
    public java.util.Optional<QuizAttempt> findById(Long id) {
        return repository.findById(id);
    }
}
