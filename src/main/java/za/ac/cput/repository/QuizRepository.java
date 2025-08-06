package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
